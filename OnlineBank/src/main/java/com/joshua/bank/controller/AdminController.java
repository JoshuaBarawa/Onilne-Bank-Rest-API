package com.joshua.bank.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joshua.bank.model.User;
import com.joshua.bank.service.AdminService;

@RestController
@RequestMapping
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/getusers", method = RequestMethod.GET)
	public List<User> getUsers(){
		return adminService.getUsers();
	}
	
	@RequestMapping(value="/getuser/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable int id){
		
		return adminService.getUser(id);
	}
}
