package com.edmond.bank.service;

import java.util.List;

import com.edmond.bank.entity.Account;

public interface AccountService {

	public List<Account> findAll();

	public Account findById(int theId);

	public void save(Account theAccount);

	public void deleteById(int theId);

}
