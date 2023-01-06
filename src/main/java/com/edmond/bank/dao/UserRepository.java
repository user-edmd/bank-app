package com.edmond.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edmond.bank.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
