package com.edmond.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edmond.bank.entity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

	@Query(value = "SELECT SUM(m.amount) FROM transactions m WHERE Account_id = :accountId", nativeQuery = true)
	Double findTotalByAccountId(@Param("accountId") int accountId);

}
