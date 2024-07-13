package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionsService {
	Optional<Transactions> findById(int id);
	Transactions save(Transactions transactions);
	void deleteById(int id);
	void transferBetweenAccounts(Account accountIdTo, Account accountIdFrom, Double amount);
	Page<Transactions> findByAccountId(int accountId, Pageable pageable);
	Page<Transactions> findAll(Pageable pageable);
	List<Transactions> findAll();
}
