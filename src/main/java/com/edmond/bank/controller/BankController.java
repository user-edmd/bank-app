package com.edmond.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.AccountTransfer;
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
		return "redirect:/user/" + userId;
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
		return "redirect:/";
	}

	@GetMapping("/user/{userId}/account/{accountId}")
	public String retrieveAccount(@PathVariable("userId") int userId, @PathVariable("accountId") int accountId,
			Model theModel) {
		Account accountTest = accountService.findById(accountId);
		theModel.addAttribute("account", accountTest);
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
		return "redirect:/";
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
		return "redirect:/user/" + userId;
	}

	@GetMapping("/user/{userId}/account/{accountId}/create-transaction")
	public String createTransaction(@PathVariable("userId") int userId, @PathVariable("accountId") int accountId,
			Model theModel) {
		User user = userService.findById(userId);
		Account account = accountService.findById(accountId);
		Transactions transactions = new Transactions();
		theModel.addAttribute("user", user);
		theModel.addAttribute("account", account);
		theModel.addAttribute("transactions", transactions);
		return "create-transaction";
	}

	@PostMapping("/user/{userId}/account/{accountId}/create-transaction")
	public String createTransactionSuccess(@PathVariable("userId") int userId, @PathVariable("accountId") int accountId,
			@ModelAttribute("transactions") Transactions transactions) {
		User user = userService.findById(userId);
		Account account = accountService.findById(accountId);
		transactions.setAccount(account);
		account.setUser(user);
		transactionsService.save(transactions);
		return "redirect:/user/" + userId + "/account/" + accountId;
	}

	@GetMapping("/user/{userId}/create-transfer")
	public String createTransfer(@PathVariable("userId") int userId, Model theModel) {
		User user = userService.findById(userId);
		theModel.addAttribute("user", user);
		theModel.addAttribute("userAccounts", user.getAccountList());
		theModel.addAttribute("accountTransfer", new AccountTransfer());
		return "create-transfer";
	}

	@PostMapping("/user/{userId}/create-transfer")
	public String createTransfer(@PathVariable("userId") int userId, @ModelAttribute("accountTransfer") AccountTransfer accountTransfer) {
		transactionsService.transferBetweenAccounts(accountService.findById(accountTransfer.getAccountIdFrom()),
				accountService.findById(accountTransfer.getAccountIdTo()), accountTransfer.getAmountToTransfer());
		return "redirect:/user/" + userId;
	}

}
