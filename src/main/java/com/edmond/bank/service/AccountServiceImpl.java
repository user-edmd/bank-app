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

	public List<Account> findAll() { return accountRepository.findAll(); }

	public Optional<Account> findById(int id) {
//		Optional<Account> result = accountRepository.findById(theId);
//		Account account;
//		if (result.isPresent()) {
//			account = result.get();
//		} else {
//			throw new RuntimeException("Did not find account id");
//		}
//		return account;
		return accountRepository.findById(id);

	}

	public Account save(Account account) {
		return accountRepository.save(account);
	}

	public void deleteById(int id) {
		accountRepository.deleteById(id);
	}

	public Account createAccount(Account account) {
		Optional<User> user = userService.findById(account.getUserId());
		if (user.isPresent()) {
			account.setUser(user.get());
			Account newAccount = save(account);
			newAccount.setAccountNumber();
			return save(newAccount);
		} else {
			throw new RuntimeException("User cannot be found");
		}
	}
}
