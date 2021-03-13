package com.joshua.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joshua.bank.dao.UserDAO;
import com.joshua.bank.model.User;

@Service
public class AdminService {
	@Autowired
	private UserDAO userDao;
	
	public List<User> getUsers(){
		return userDao.getUsers();
	}
	
	public User getUser(int id) {
		return userDao.getUser(id);
	}
}
