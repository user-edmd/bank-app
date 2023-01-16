package com.edmond.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edmond.bank.dao.AccountRepository;
import com.edmond.bank.dao.TransactionsRepository;
import com.edmond.bank.dao.UserRepository;
import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.TransactionsService;
import com.edmond.bank.service.UserService;

@Controller
public class BankController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionsService transactionsService;

	@GetMapping("/")
	public String home(Model theModel) {
		List<User> allUsers = userService.findAll();
		theModel.addAttribute("options", allUsers);
		return "index";
	}

	@GetMapping("/user/{userId}")
	public String retrieveUser(@PathVariable("userId") int userId, Model theModel) {
		User userTest = userService.findById(userId);
		theModel.addAttribute("user", userTest);
		theModel.addAttribute("userAccounts", userTest.getAccountList());
		return "user";
	}
	
	@GetMapping("/user/{userId}/edit")
	public String editUser(@PathVariable("userId") int userId, Model theModel) {
		User userTest = userService.findById(userId);
		theModel.addAttribute("user", userTest);
		return "edit-user";
	}
	
	@PostMapping("/user/{userId}/edit")
	public String editUserSuccess(@ModelAttribute("user") User user, @PathVariable("userId") int userId) {
		User tempUser = userService.findById(userId);
		tempUser.setFirstName(user.getFirstName());
		tempUser.setLastName(user.getLastName());
		tempUser.setAddress(user.getAddress());
		tempUser.setDob(user.getDob());
		tempUser.setSsn(user.getSsn());
		userService.save(tempUser);
		return "edit-user-success";
	}
	
	@GetMapping("/user/{userId}/delete")
	public String deleteUser(@PathVariable("userId") int userId, Model theModel) {
		User userTest = userService.findById(userId);
		theModel.addAttribute("user", userTest);
		return "delete-user";
	}
	
	@PostMapping("/user/{userId}/delete")
	public String deleteUserSuccess(@ModelAttribute("user") User user, @PathVariable("userId") int userId) {
		userService.deleteById(userId);
		return "delete-user-success";
	}
	
	@GetMapping("/user/{userId}/account/{accountId}")
	public String retrieveAccount(@PathVariable("userId") int userId, @PathVariable("accountId") int accountId,Model theModel) {
		Account accountTest = accountService.findById(accountId);
		theModel.addAttribute("transactions", accountTest.getTransactionsList());
		theModel.addAttribute("transactionsBalance", transactionsService.findTotalByAccountId(accountId));
		return "account";
	}
	
	
	@GetMapping("/create-user")
	public String createUser(Model theModel) {
		User user = new User();
		theModel.addAttribute("user", user);
		return "create-user";
	}
	
	@PostMapping("/create-user")
	public String createUserSuccess(@ModelAttribute("user") User user) {
	    userService.save(user);
	    return "create-user-success";
	}
	
	@GetMapping("/user/{userId}/create-account")
	public String createAccount(@PathVariable("userId") int userId, Model theModel) {
		User user = userService.findById(userId);
		Account account = new Account();
		theModel.addAttribute("user", user);
		theModel.addAttribute("account", account);
		return "create-account";
	}
	
	@PostMapping("/user/{userId}/create-account")
	public String createAccountSuccess(@PathVariable("userId") int userId, @ModelAttribute("account") Account account) {
		User user = userService.findById(userId);
		account.setUser(user);
		accountService.save(account);
		return "create-account-success";
	}


}
