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

	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	public User createUser(User user) {
		Optional<User> result = userRepository.findByUsername(user.getUsername());
		if (result.isPresent())
			throw new RuntimeException("Username already exists: " + user.getUsername());
		return save(user);
	}

	public User editUser(User updatedUser) {
		Optional<User> foundUser = userRepository.findById(updatedUser.getId());
		if (foundUser.isPresent()) {
			foundUser.get().setUsername(updatedUser.getUsername());
			foundUser.get().setFirstName(updatedUser.getFirstName());
			foundUser.get().setLastName(updatedUser.getLastName());
			foundUser.get().setAddress(updatedUser.getAddress());
			foundUser.get().setDob(updatedUser.getDob());
			foundUser.get().setSsn(updatedUser.getSsn());
			foundUser.get().setUsername(updatedUser.getUsername());
			return this.userRepository.save(foundUser.get());
		} else {
			throw new RuntimeException("User does not exist.");
		}
	}

	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByUsername(email);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

}
