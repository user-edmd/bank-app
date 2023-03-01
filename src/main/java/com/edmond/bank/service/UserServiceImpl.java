package com.edmond.bank.service;

import java.util.List;
import java.util.Optional;

import com.edmond.bank.model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmond.bank.dao.UserRepository;
import com.edmond.bank.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(int theId) {
		Optional<User> result = userRepository.findById(theId);
		User user = null;
		if (result.isPresent()) {
			user = result.get();
		} else {
			throw new RuntimeException("Did not find user id");
		}
		return user;
	}

	public void save(User theUser) {
		userRepository.save(theUser);
	}

	public void deleteById(int theId) {
		userRepository.deleteById(theId);
	}

	public void createUser(UserForm userForm) {
		User user = new User();
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setAddress(userForm.getAddress());
		user.setDob(userForm.getDob());
		user.setSsn(userForm.getSsn());
		save(user);
	}

}
