package com.edmond.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private UserRepository userRepository;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/edmond")
	public String edmondsAccount(Model theModel) {
		User userEdmond = userService.findById(2);
		theModel.addAttribute("user", userEdmond);
		theModel.addAttribute("account", userEdmond.getAccountList().get(0).getTransactionsList());

		return "edmond";

	}

	@GetMapping("/test")
	public String testPage(Model theModel) {
		List<User> userTest = userRepository.findAll();
		theModel.addAttribute("options", userTest);

		return "test";

	}

	@GetMapping("test/{id}")
	public String retrieveUser(@PathVariable("id") int id, Model theModel) {

		User userTest = userService.findById(id);
		theModel.addAttribute("user", userTest);
		theModel.addAttribute("userAccounts", userTest.getAccountList());
		theModel.addAttribute("userTransactions", userTest.getAccountList());

		return "testuserpage";
	}

}
