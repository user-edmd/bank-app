package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import com.edmond.bank.entity.User;
import com.edmond.bank.model.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmond.bank.dao.AccountRepository;
import com.edmond.bank.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserService userService;

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public Account findById(int theId) {
		Optional<Account> result = accountRepository.findById(theId);
		Account account = null;
		if (result.isPresent()) {
			account = result.get();
		} else {
			throw new RuntimeException("Did not find account id");
		}
		return account;

	}

	public void save(Account theAccount) {
		accountRepository.save(theAccount);
	}

	public void deleteById(int theId) {
		accountRepository.deleteById(theId);
	}

	public void createAccount(int userId, AccountForm accountForm) {
		User user = userService.findById(userId);
		Account account = new Account();
		account.setAccountType(accountForm.getAccountType());
		account.setUser(user);
		Optional<String> optionalAccountNumber = Optional.ofNullable(account.getAccountNumber());
		if (!optionalAccountNumber.isPresent())
			account.setAccountNumber(String.valueOf((long) (Math.random() * 10000000000000000L)));
		save(account);
	}

}
