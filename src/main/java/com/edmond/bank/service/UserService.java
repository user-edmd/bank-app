package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import com.edmond.bank.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
	Page<User> findAll(Pageable pageable);
	List<User> findAll();
	User findById(int theId);
	User save(User user);
	void deleteById(int theId);
	User createUser(User user);
	User editUser(User user);
	User findUserByEmail(String email);
}
