package com.joshua.bank.model;

public class Account {

	private int idNumber;
	private int accountBalance;
	private int depositAmount;
	private int withDrawAmount;
	private int transferAmount;
	private int phoneNumber;

	public Account() {

	}

	public Account(int idNumber, int accountBalance,int phoneNumber) {

		this.idNumber = idNumber;
		this.accountBalance = accountBalance;
		this.phoneNumber = phoneNumber;
	
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public int getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(int depositAmount) {
		this.depositAmount = depositAmount;
	}

	public int getWithDrawAmount() {
		return withDrawAmount;
	}

	public void setWithDrawAmount(int withDrawAmount) {
		this.withDrawAmount = withDrawAmount;
	}

	public int getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(int transferAmount) {
		this.transferAmount = transferAmount;
	}
	

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Account [idNumber=" + idNumber + ", accountBalance=" + accountBalance
				+ ", phoneNumber=" + phoneNumber + "]";
	}

}
