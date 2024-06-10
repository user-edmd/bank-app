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

	public User findById(int theId) {
		Optional<User> result = userRepository.findById(theId);
		User user;
		if (result.isPresent()) {
			user = result.get();
		} else {
			throw new RuntimeException("Did not find user id");
		}
		return user;
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	public User createUser(User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setAddress(user.getAddress());
		newUser.setDob(user.getDob());
		newUser.setSsn(user.getSsn());
		save(newUser);
		return newUser;
	}

	public void editUser(User updatedUser) {
		User user = findById(updatedUser.getId());
		user.setUsername(updatedUser.getUsername());
		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());
		user.setAddress(updatedUser.getAddress());
		user.setDob(updatedUser.getDob());
		user.setSsn(updatedUser.getSsn());
		save(user);
	}

	public User findUserByEmail(String email) {
		return userRepository.findByUsername(email);
	}

}
