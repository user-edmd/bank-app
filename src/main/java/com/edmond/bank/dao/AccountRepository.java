package com.edmond.bank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edmond.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	List<Account> findByUserId(int userId);

}
