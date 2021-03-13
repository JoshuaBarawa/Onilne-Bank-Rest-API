package com.joshua.bank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.joshua.bank.model.Account;
import com.joshua.bank.model.User;

@Component
public class UserDAO {



	private NamedParameterJdbcTemplate jdbcTemp;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setJdbcTemp(DataSource dataSource) {
		this.jdbcTemp = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<User> getUsers() {
		
		return jdbcTemp.query("select * from users", new RowMapper<User>() {
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user = new User();
				user.setUserName(rs.getString(1));
				user.setIdNumber(rs.getInt(2));
				user.setEmailAddress(rs.getString(3));
				user.setPhoneNumber(rs.getInt(4));
				user.setAuthority(rs.getString(5));
				user.setPassword(rs.getString(6));
				
				return user;
			}
		
		});
	}
	
	public User getUser(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource("id", id);
		return jdbcTemp.queryForObject("select * from users where idnumber=:id", param, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int arg1) throws SQLException {
				User user = new User();
				user.setUserName(rs.getString(1));
				user.setIdNumber(rs.getInt(2));
				user.setEmailAddress(rs.getString(3));
				user.setPhoneNumber(rs.getInt(4));
				user.setAuthority(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setEnabled(rs.getBoolean(7));
				return user;

			}
		});
	}

	public boolean createUser(User user) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		
		param.addValue("userName", user.getUserName());
		param.addValue("idNumber", user.getIdNumber());
		param.addValue("emailAddress", user.getEmailAddress());
		param.addValue("phoneNumber", user.getPhoneNumber());
		param.addValue("password", passwordEncoder.encode(user.getPassword()));
		param.addValue("authority", user.getAuthority());
        param.addValue("enabled", user.isEnabled());
        
      
     
        	 jdbcTemp.update("INSERT INTO users (username,idnumber,emailaddress,phonenumber,password,authority,enabled)"
     				+ "VALUES (:userName,:idNumber,:emailAddress,:phoneNumber,:password,:authority,:enabled)", param);
     	 
     	 createAccount(new Account(user.getIdNumber(),0,user.getPhoneNumber()));
     	 sendEmail(user.getEmailAddress());
     	 
     	 
     	return true;
       
		
	}
	
	  public boolean createAccount(Account account) {
	 	
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("idNumber", account.getIdNumber());
		param.addValue("accountBalance", account.getAccountBalance());
		param.addValue("phoneNumber", account.getPhoneNumber());
		
		jdbcTemp.update("INSERT into account (idnumber,accountbalance,phonenumber) VALUE(:idNumber,:accountBalance,:phoneNumber)", param);
	
		
		return true;
	}

	public boolean updateUser(User user) {
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(user);
		return jdbcTemp.update("UPDATE users set username=:userName,idnumber=:idNumber,emailaddress=:emailAddress,phonenumber=:phoneNumber,password=:password,authority=:authority"
				+ " WHERE idnumber=:idNumber", param) == 1;
	}
	
	public int checkBalance(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource("id", id);
		
		return jdbcTemp.queryForObject("select * from account where idnumber=:id", param, new RowMapper<Integer>() {
			public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Account account = new Account();
				return account.getAccountBalance();
				
			}
		});				
	}

	public boolean closeAccount(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource("id", id);
		
		       jdbcTemp.update("delete from users where idnumber=:id", param);
		       jdbcTemp.update("delete from account where idnumber=:id", param);
		       jdbcTemp.update("delete from transactions where idnumber=:id", param);
		       
		       return true;
	}
	
	
	
	public boolean sendEmail(String email) {
		
		String toEmail = email;
		String fromEmail = "dminjoshuabm@gmail.com";
		String password = "Bank@123";
		try {
	    Properties prop = new Properties();	
	    prop.setProperty("mail.smtp.host", "smtp.gmail.com");
	    prop.setProperty("mail.smtp.port", "465");
	    prop.setProperty("mail.smtp.auth", "true");
	    prop.setProperty("mail.smtp.ssl.enable", "true");
	   
	    
	    Session session = Session.getInstance(prop, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
	    
	    session.setDebug(true);
	    
	    MimeMessage message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(fromEmail));
	    message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
	    message.setSubject("Eamil Varification");
	    message.setText("Please Verify Your Email Address Uisng This Link: " +"http://localhost:8080/onlinebank/login");
	    
	    Transport.send(message);
	    
	    System.out.println("Sending Email..........");
	    System.out.println("Email sent sent succesful to: " + email);
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		return true;	
	}
	
	public String getVerificationCode() {
		Random random = new Random();
		int code = random.nextInt(999999);
		
		return String.format("%06d", code);
		
	}
	
}
