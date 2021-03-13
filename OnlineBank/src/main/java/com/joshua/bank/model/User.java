package com.joshua.bank.model;

public class User {

	
	private String userName;
	private String emailAddress;
	private int idNumber;
	private int phoneNumber;
	private String password;
	private boolean enabled = true;
	private String authority;
	private int code;


	public User() {

	}
	
	public User(String userName, String emailAddress, int idNumber, int phoneNumber, String password,
			String authority) {
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.idNumber = idNumber;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.authority = authority;
	
	}




	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public int getIdNumber() {
		return idNumber;
	}


	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}


	public int getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}



	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", emailAddress=" + emailAddress + ", idNumber=" + idNumber
				+ ", phoneNumber=" + phoneNumber + ", password=" + password + ", enabled=" + enabled + ", authority="
				+ authority + "]";
	}
	
	

	
}
