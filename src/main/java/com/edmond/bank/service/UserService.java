package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import com.edmond.bank.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
	Page<User> findAll(Pageable pageable);
	List<User> findAll();
	Optional<User> findById(int id);
	User save(User user);
	void deleteById(int id);
	User createUser(User user);
	User editUser(User user);
	Optional<User> findUserByEmail(String email);
}
