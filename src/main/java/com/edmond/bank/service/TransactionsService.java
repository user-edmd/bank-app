package com.edmond.bank.service;

import java.util.List;

import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.Transactions;
import com.edmond.bank.model.TransactionsForm;
import org.springframework.data.domain.Page;

public interface TransactionsService {

	public List<Transactions> findAll();

	public Transactions findById(int theId);

	public void save(Transactions transactions);

	public void deleteById(int theId);

	public Double findTotalByAccountId(int accountId);

	public void transferBetweenAccounts(Account accountIdTo, Account accountIdFrom, Double amount);

	public void createTransaction(int accountId, int userId, TransactionsForm transactionsForm);

	public Page<Transactions> findByAccountId(int accountId);

}
