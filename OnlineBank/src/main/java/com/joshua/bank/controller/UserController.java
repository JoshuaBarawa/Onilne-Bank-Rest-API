package com.joshua.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joshua.bank.model.Account;
import com.joshua.bank.model.MiniStatement;
import com.joshua.bank.model.User;
import com.joshua.bank.service.UserService;

@RestController
@RequestMapping
public class UserController {
 
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value ="/home", method=RequestMethod.POST)
	public String home() {
		return "******Welcome to our online bank system********";
	}
	
	@RequestMapping(value ="/createaccount", method=RequestMethod.POST)
	public String createAccount(@RequestBody User user, BindingResult result) {
		
		try {	
			userService.createUser(user);
			 return "Account Created Succceful";
            
		}
		catch(DuplicateKeyException ex) {
			result.rejectValue("idNumber", "DuplicateKey.user.idNumber", "ID Number already exists!");
			 return result.getFieldError().toString();
			
		}
		
	}
	
	@RequestMapping(value = "/updateaccount/{id}", method=RequestMethod.PUT)
	public boolean updateUser(@RequestBody User user, @PathVariable int id) {
		return userService.updateUser(user);
	}
	
	@RequestMapping(value = "/checkbalance/{id}", method=RequestMethod.GET)
	public int checkBalance( @PathVariable int id) {
		return userService.checkBalance(id);
	}
	
	@RequestMapping(value = "/closeaccount/{id}", method=RequestMethod.DELETE)
	public boolean closeAccount(@PathVariable int id) {
		return userService.closeAccount(id);
	}
	
	@RequestMapping(value = "/depositamount/{id}", method=RequestMethod.PUT)
	public int depositAmount(@RequestBody Account account, @PathVariable int id) {
		return userService.depositAmount(account.getDepositAmount(), id);
		 
	}
	
	@RequestMapping(value = "/withdrawamount/{id}", method=RequestMethod.PUT)
	public int withDrawAmount(@RequestBody Account account, @PathVariable int id) {
		 return userService.withDrawAmount(account.getWithDrawAmount(), id);
	}
	
	@RequestMapping(value = "/transferamount/{id}", method=RequestMethod.PUT)
	public int transferAmount(@RequestBody  Account account, @PathVariable int id) {
		 return userService.transferAmount(account.getPhoneNumber(),account.getTransferAmount(), id);

	}
	
	@RequestMapping(value = "/statements/{id}", method = RequestMethod.GET)
	public List<MiniStatement> getStatements(@PathVariable int id) {
		return userService.getStatements(id);
	}
}
