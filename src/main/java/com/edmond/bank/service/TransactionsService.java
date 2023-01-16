package com.edmond.bank.service;

import java.util.List;

import com.edmond.bank.entity.Transactions;

public interface TransactionsService {
	
	public List<Transactions> findAll();
	
	public Transactions findById(int theId);
	
	public void save(Transactions transactions);
	
	public void deleteById(int theId);
	
	public Double findTotalByAccountId(int accountId);

}
