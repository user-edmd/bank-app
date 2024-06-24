package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edmond.bank.dao.UserRepository;
import com.edmond.bank.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public User findById(int id) {
		Optional<User> result = userRepository.findById(id);
		User user;
		if (result.isPresent()) {
			user = result.get();
		} else {
			throw new RuntimeException("Did not find user id");
		}
		return user;
	}

	public User save(User user) {
		Optional<User> result = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
		if (result.isPresent())
			throw new RuntimeException("Username already exists: " + user.getUsername());
		return userRepository.save(user);
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	public User createUser(User newUser) {
		return save(newUser);
	}

	public User editUser(User updatedUser) {
		return save(updatedUser);
	}

	public User findUserByEmail(String email) {
		return userRepository.findByUsername(email);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

}
