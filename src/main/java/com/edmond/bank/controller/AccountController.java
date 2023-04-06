package com.edmond.bank.controller;

import java.util.List;

import com.edmond.bank.entity.User;
import com.edmond.bank.model.AccountForm;
import com.edmond.bank.service.TransactionsService;
import com.edmond.bank.service.UserDetailsService;
import com.edmond.bank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.edmond.bank.entity.Account;
import com.edmond.bank.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionsService transactionsService;

	@GetMapping("/accounts/list")
	public String listUsers(Model theModel) {
		List<Account> theAccounts = accountService.findAll();
		theModel.addAttribute("account", theAccounts);
		return "list-accounts";
	}

	@GetMapping("/user/{userId}/create-account")
	public String createAccount(@PathVariable("userId") int userId, Model model, Authentication auth) {
		UserDetailsService userDetailsService = (UserDetailsService) auth.getPrincipal();
		User user = userService.findUserByEmail(userDetailsService.getUsername());
		if (user.getId() != userId)
			throw new UserNotAuthorized("Unauthorized");
		Account account = new Account();
		model.addAttribute("user", user);
		model.addAttribute("account", account);
		return "create-account";
	}

	@PostMapping("/user/{userId}/create-account")
	public String createAccountSuccess(@PathVariable("userId") int userId, @Valid @ModelAttribute("account") AccountForm accountForm,
									   BindingResult result) {
		if (result.hasErrors()) {
			return "create-account";
		}
		accountService.createAccount(userId, accountForm);
		return "redirect:/user/" + userId;
	}

	@GetMapping("/user/{userId}/account/{accountId}")
	public String retrieveAccount(@PathVariable("userId") int userId, @PathVariable("accountId") int accountId,
								  Model model, Authentication auth) {
		UserDetailsService userDetailsService = (UserDetailsService) auth.getPrincipal();
		User user = userService.findUserByEmail(userDetailsService.getUsername());
		boolean userOwnedAccount = user.getAccountList().stream().anyMatch(account -> accountId == account.getId());

		if (user.getId() != userId || !userOwnedAccount) {
			throw new RuntimeException("Unauthorized");
		}

		Account account = accountService.findById(accountId);
		model.addAttribute("user", user);
		model.addAttribute("account", account);
		model.addAttribute("transactions", account.getTransactionsList());
		model.addAttribute("transactionsBalance", transactionsService.findTotalByAccountId(accountId));
		return "account";
	}

	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "User not authorized")
	protected class UserNotAuthorized extends RuntimeException {
		public UserNotAuthorized(final String message) {
			super(message);
		}
	}
}