package com.edmond.bank.controller;

import java.util.List;

import com.edmond.bank.model.AccountForm;
import com.edmond.bank.model.TransactionsForm;
import com.edmond.bank.model.UserForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.edmond.bank.entity.Account;
import com.edmond.bank.model.AccountTransfer;
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
	public String home(Model model) {
		List<User> allUsers = userService.findAll();
		model.addAttribute("options", allUsers);
		return "index";
	}

	@GetMapping("/user/{userId}")
	public String retrieveUser(@PathVariable("userId") int userId, Model model) {
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		model.addAttribute("userAccounts", user.getAccountList());
		return "user";
	}

	@GetMapping("/user/{userId}/edit")
	public String editUser(@PathVariable("userId") int userId, Model model) {
		User user = userService.findById(userId);
		model.addAttribute("user", user);
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
	public String deleteUser(@PathVariable("userId") int userId, Model model) {
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		return "delete-user";
	}

	@PostMapping("/user/{userId}/delete")
	public String deleteUserSuccess(@ModelAttribute("user") User user, @PathVariable("userId") int userId) {
		userService.deleteById(userId);
		return "redirect:/";
	}

	@GetMapping("/user/{userId}/account/{accountId}")
	public String retrieveAccount(@PathVariable("userId") int userId, @PathVariable("accountId") int accountId,
			Model model) {
		Account account = accountService.findById(accountId);
		model.addAttribute("account", account);
		model.addAttribute("transactions", account.getTransactionsList());
		model.addAttribute("transactionsBalance", transactionsService.findTotalByAccountId(accountId));
		return "account";
	}

	@GetMapping("/create-user")
	public String createUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "create-user";
	}

	@PostMapping("/create-user")
	public String createUserSuccess(@Valid @ModelAttribute("user") UserForm userForm, BindingResult result) {
		if (result.hasErrors()) {
			return "create-user";
		}
		userService.createUser(userForm);
		return "redirect:/";
	}

	@GetMapping("/user/{userId}/create-account")
	public String createAccount(@PathVariable("userId") int userId, Model model) {
		User user = userService.findById(userId);
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

	@GetMapping("/user/{userId}/account/{accountId}/create-transaction")
	public String createTransaction(@PathVariable("userId") int userId, @PathVariable("accountId") int accountId,
			Model model) {
		User user = userService.findById(userId);
		Account account = accountService.findById(accountId);
		Transactions transactions = new Transactions();
		model.addAttribute("user", user);
		model.addAttribute("account", account);
		model.addAttribute("transactions", transactions);
		return "create-transaction";
	}

	@PostMapping("/user/{userId}/account/{accountId}/create-transaction")
	public String createTransactionSuccess(@PathVariable("userId") int userId, @PathVariable("accountId") int accountId,
			@ModelAttribute("transactions") TransactionsForm transactionsForm) {
		transactionsService.createTransaction(accountId, userId, transactionsForm);
		return "redirect:/user/" + userId + "/account/" + accountId;
	}

	@GetMapping("/user/{userId}/create-transfer")
	public String createTransfer(@PathVariable("userId") int userId, Model model) {
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		model.addAttribute("userAccounts", user.getAccountList());
		model.addAttribute("accountTransfer", new AccountTransfer());
		return "create-transfer";
	}

	@PostMapping("/user/{userId}/create-transfer")
	public String createTransfer(@PathVariable("userId") int userId, @ModelAttribute("accountTransfer") AccountTransfer accountTransfer) {
		transactionsService.transferBetweenAccounts(accountService.findById(accountTransfer.getAccountIdFrom()),
				accountService.findById(accountTransfer.getAccountIdTo()), accountTransfer.getAmountToTransfer());
		return "redirect:/user/" + userId;
	}

}
