package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import com.edmond.bank.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edmond.bank.dao.AccountRepository;
import com.edmond.bank.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserService userService;

	public Page<Account> findAll(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	public Account findById(int theId) {
		Optional<Account> result = accountRepository.findById(theId);
		Account account;
		if (result.isPresent()) {
			account = result.get();
		} else {
			throw new RuntimeException("Did not find account id");
		}
		return account;

	}

	public void save(Account account) {
		accountRepository.save(account);
	}

	public void deleteById(int id) {
		accountRepository.deleteById(id);
	}

	public void createAccount(Account account) {
		User user = userService.findById(account.getUserId());
		account.setUser(user);
		account.setAccountType(account.getAccountType());
		save(account);
		account.setAccountNumber();
		save(account);
	}
}
