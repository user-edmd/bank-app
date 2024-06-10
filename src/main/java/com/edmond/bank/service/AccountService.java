package com.edmond.bank.service;

import java.util.List;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.User;
import com.edmond.bank.model.AccountForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

	public Page<Account> findAll(Pageable pageable);

	public Account findById(int theId);

	public void save(Account theAccount);

	public void deleteById(int theId);

	public void createAccount(Account account);

}
