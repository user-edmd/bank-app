package com.edmond.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edmond.bank.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
