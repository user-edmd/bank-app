package com.edmond.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.UserService;

@Controller
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/list")
	public String listUsers(Model theModel) {
		List<Account> theAccounts = accountService.findAll();
		theModel.addAttribute("account", theAccounts);
		return "list-accounts";
	}
}