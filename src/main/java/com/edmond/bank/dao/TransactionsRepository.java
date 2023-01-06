package com.edmond.bank.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edmond.bank.entity.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
	
	List<Transactions> findByAccountId(int accountId);

}
