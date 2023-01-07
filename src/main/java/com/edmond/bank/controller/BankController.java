package com.edmond.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/edmond")
	public String edmondsAccount(Model theModel) {
		User userEdmond = userService.findById(2);
		theModel.addAttribute("user", userEdmond);
		theModel.addAttribute("account", userEdmond.getAccountList());
		theModel.addAttribute("transactions", userEdmond.getAccountList().get(0).getTransactionsList());
		
		return "edmond";
		
	}
	

}
