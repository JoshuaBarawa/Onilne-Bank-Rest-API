package com.joshua.bank.model;

import java.util.Date;


public class MiniStatement {

	private int phoneNumber;
	private String type;
	private int amount;
	private int recipient;
	private Date date;
	private int accountBalance;

	public MiniStatement() {

	}

	public MiniStatement(int phoneNumber,String type, int amount, int recipient, Date date, int accountBalance) {
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.amount = amount;
		this.recipient = recipient;
		this.date = date;
		this.accountBalance = accountBalance;
	}
	

	
	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getRecipient() {
		return recipient;
	}

	public void setRecipient(int recipient) {
		this.recipient = recipient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "MiniStatement [idNumber=" + phoneNumber + ", type=" + type + ", amount=" + amount + ", recipient="
				+ recipient + ", date=" + date + ", accountBalance=" + accountBalance + "]";
	}

}
