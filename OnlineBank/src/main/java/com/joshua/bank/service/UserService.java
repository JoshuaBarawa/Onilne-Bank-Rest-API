package com.joshua.bank.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joshua.bank.dao.TransactionDAO;
import com.joshua.bank.dao.UserDAO;
import com.joshua.bank.model.MiniStatement;
import com.joshua.bank.model.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private TransactionDAO transDao;

	
	public boolean createUser(User user) {
		return userDao.createUser(user);
	}

	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	
	public int checkBalance(int id) {
		return userDao.checkBalance(id);
	}
	
	public boolean closeAccount(int id) {
		return userDao.closeAccount(id);
	}
	
	public int depositAmount(int amount, int id) {
	 return transDao.depositAmount(amount, id);

		}
	
	public int withDrawAmount(int amount, int id) {
		return transDao.withDrawAmount(amount, id);
	}
	
	public int transferAmount(int reciever,int amount, int id) {
		return transDao.transferAmount(reciever, amount, id);
	}
	
   public List<MiniStatement> getStatements(int id) {
	return transDao.getStatements(id);
}
 
}
