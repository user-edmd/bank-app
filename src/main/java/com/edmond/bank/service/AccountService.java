package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.User;
import com.edmond.bank.model.AccountForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
	Page<Account> findAll(Pageable pageable);
	List<Account> findAll();
	Optional<Account> findById(int id);
	Account save(Account account);
	void deleteById(int id);
	Account createAccount(Account account);
}
