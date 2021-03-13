package com.joshua.bank.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.joshua.bank.model.Account;
import com.joshua.bank.model.MiniStatement;

@Component
public class TransactionDAO {

private NamedParameterJdbcTemplate jdbcTemp;


	@Autowired
	public void setJdbcTemp(DataSource dataSource) {
		this.jdbcTemp = new NamedParameterJdbcTemplate(dataSource);
	}
	
	
	public Account getUser(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource("id", id);
		return jdbcTemp.queryForObject("select * from account where phonenumber=:id", param, new RowMapper<Account>() {
			@Override
			public Account mapRow(ResultSet rs, int arg1) throws SQLException {
                  Account account = new Account();
				  account.setAccountBalance(rs.getInt(2));

				return account;
			}
		});
	}
	



	public int depositAmount(int amount, int id) {
	      Account account = getUser(id);
	      account.setDepositAmount(amount);
	      int newBalance = 0;
	      if(account.getDepositAmount() < 0) {
	    	  System.out.println("Deposit Amount must be equal or greater than ksh.200!");
	      }
	      else {
		   newBalance = account.getAccountBalance() + account.getDepositAmount();
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		param.addValue("newBalance", newBalance);

		
		jdbcTemp.update("UPDATE account set accountbalance=:newBalance"
				+ " WHERE phonenumber=:id", param);
		saveTransaction(new MiniStatement(id, "Deposit Money",amount,0,new Date(),newBalance));
	      }
		return newBalance;
	      
	}
	
	public int withDrawAmount(int amount, int id) {
		Account account = getUser(id);
		account.setWithDrawAmount(amount);
		int newBalance =0;
		if(account.getWithDrawAmount() > account.getAccountBalance()) {
			System.out.println("You have insufficient Amount in your acccount!!" +"\n" + "Current Balance: " + account.getAccountBalance());
		}
		else {
		 newBalance = account.getAccountBalance() - account.getWithDrawAmount();
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		param.addValue("newBalance", newBalance);
		
		jdbcTemp.update("UPDATE account set accountbalance=:newBalance"
				+ " WHERE phonenumber=:id", param);
		saveTransaction(new MiniStatement(id, "Withdraw Money",account.getWithDrawAmount(),0,new Date(),newBalance));
		}
		return newBalance;
	}
	
	
	public int transferAmount(int reciever, int amount, int id) {

		Account  account = getUser(id);
		Account account2 = getUser(reciever);
		
        account2.setPhoneNumber(reciever);
		account.setTransferAmount(amount);
		
		if(account.getTransferAmount() > account.getAccountBalance()) {
			System.out.println("Insufficient Account Balance to complete transaction!!" +"\n"+"Current Account Balance: " + account.getAccountBalance());
		}
		
		else if(account.getTransferAmount() <= 0) {
			System.out.println("Check your transfer amount and try again!!");
		}
		else {
		
			int newBalance = withDrawAmount(amount, id);
           depositAmount(amount,reciever);
           
          
            saveTransaction(new MiniStatement(id, "Transfer Money",amount,reciever,new Date(),account.getAccountBalance()));
            return newBalance;
		}
		return account.getAccountBalance();
	}
	
	public boolean saveTransaction(MiniStatement statement) {
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(statement);
		System.out.println(statement.toString());
		return jdbcTemp.update("INSERT into transactions (phonenumber,type,amount,recipient,date,accountbalance) VALUES (:phoneNumber,:type,:amount,:recipient,:date,:accountBalance)", param) == 1;
	}
	
	
	
	public List<MiniStatement> getStatements(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource("id",id);
		
		return  jdbcTemp.query("select * from transactions where phonenumber=:id", param,new RowMapper<MiniStatement>() {
			public MiniStatement mapRow(ResultSet rs, int arg1) throws SQLException {
				
				MiniStatement statement = new MiniStatement();
				
				statement.setPhoneNumber(rs.getInt(1));
				statement.setType(rs.getString(2));
				statement.setAmount(rs.getInt(3));
				statement.setRecipient(rs.getInt(4));
				statement.setDate(rs.getDate(5));
				statement.setAccountBalance(rs.getInt(6));
				
				return statement;
			}
		});
	}
}
