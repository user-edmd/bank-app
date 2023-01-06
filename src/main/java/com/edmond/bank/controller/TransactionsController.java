package com.edmond.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.AccountService;
import com.edmond.bank.service.TransactionsService;
import com.edmond.bank.service.UserService;

@Controller
@RequestMapping("/transactions")
public class TransactionsController {
	
	@Autowired
	private TransactionsService transactionsService;
	
	@GetMapping("/list")
	public String listUsers(Model theModel) {
		List<Transactions> theTransactions = transactionsService.findAll();
		theModel.addAttribute("transactions", theTransactions);
		return "list-transactions";
	}
}