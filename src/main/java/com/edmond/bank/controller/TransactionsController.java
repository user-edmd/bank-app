//package com.edmond.bank.controller;
//
//import java.util.List;
//
//import com.edmond.bank.entity.Account;
//import com.edmond.bank.entity.User;
//import com.edmond.bank.model.AccountTransfer;
//import com.edmond.bank.model.TransactionsForm;
//import com.edmond.bank.service.AccountService;
//import com.edmond.bank.service.UserDetailsService;
//import com.edmond.bank.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import com.edmond.bank.entity.Transactions;
//import com.edmond.bank.service.TransactionsService;
//
//@Controller
//public class TransactionsController {
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private AccountService accountService;
//
//	@Autowired
//	private TransactionsService transactionsService;
//
//	@GetMapping("/list")
//	public String listUsers(Model theModel) {
//		List<Transactions> theTransactions = transactionsService.findAll();
//		theModel.addAttribute("transactions", theTransactions);
//		return "list-transactions";
//	}
//
//	@GetMapping("/user/{userId}/account/{accountId}/create-transaction")
//	public String createTransaction(@PathVariable("userId") int userId, @PathVariable("accountId") int accountId,
//									Model model, Authentication auth) {
//		UserDetailsService userDetailsService = (UserDetailsService) auth.getPrincipal();
//		User user = userService.findUserByEmail(userDetailsService.getUsername());
//		boolean userOwnedAccount = user.getAccountList().stream().anyMatch(account -> accountId == account.getId());
//		if (user.getId() != userId || !userOwnedAccount)
//			throw new RuntimeException("Unauthorized");
//		Account account = accountService.findById(accountId);
//		Transactions transactions = new Transactions();
//		model.addAttribute("user", user);
//		model.addAttribute("account", account);
//		model.addAttribute("transactions", transactions);
//		return "create-transaction";
//	}
//
//	@PostMapping("/user/{userId}/account/{accountId}/create-transaction")
//	public String createTransactionSuccess(@PathVariable("userId") int userId, @PathVariable("accountId") int accountId,
//										   @ModelAttribute("transactions") TransactionsForm transactionsForm) {
//		transactionsService.createTransaction(accountId, userId, transactionsForm);
//		return "redirect:/user/" + userId + "/account/" + accountId;
//	}
//
//	@GetMapping("/user/{userId}/create-transfer")
//	public String createTransfer(@PathVariable("userId") int userId, Model model, Authentication auth) {
//		UserDetailsService userDetailsService = (UserDetailsService) auth.getPrincipal();
//		User user = userService.findUserByEmail(userDetailsService.getUsername());
//		if (user.getId() != userId) {
//			throw new RuntimeException("Unauthorized");
//		}
//		model.addAttribute("user", user);
//		model.addAttribute("userAccounts", user.getAccountList());
//		model.addAttribute("accountTransfer", new AccountTransfer());
//		return "create-transfer";
//	}
//
//	@PostMapping("/user/{userId}/create-transfer")
//	public String createTransfer(@PathVariable("userId") int userId, @ModelAttribute("accountTransfer") AccountTransfer accountTransfer) {
//		transactionsService.transferBetweenAccounts(accountService.findById(accountTransfer.getAccountIdFrom()),
//				accountService.findById(accountTransfer.getAccountIdTo()), accountTransfer.getAmountToTransfer());
//		return "redirect:/user/" + userId;
//	}
//}