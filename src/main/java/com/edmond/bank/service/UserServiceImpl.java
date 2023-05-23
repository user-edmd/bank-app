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

//	public void createUser(UserForm userForm) {
//		User user = new User();
//		user.setUsername(userForm.getUsername());
//		user.setPassword(userForm.getPassword());
//		user.setFirstName(userForm.getFirstName());
//		user.setLastName(userForm.getLastName());
//		user.setAddress(userForm.getAddress());
//		user.setDob(userForm.getDob());
//		user.setSsn(userForm.getSsn());
//		save(user);
//	}

	public User createUser(User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setAddress(user.getAddress());
		newUser.setDob(user.getDob());
		newUser.setSsn(user.getSsn());
		newUser.setRole("USER");
		save(newUser);
		return newUser;
	}
//	public void editUser(int userId, UserForm userForm) {
//		User user = findById(userId);
//		user.setUsername(userForm.getUsername());
//		user.setPassword(userForm.getPassword());
//		user.setFirstName(userForm.getFirstName());
//		user.setLastName(userForm.getLastName());
//		user.setAddress(userForm.getAddress());
//		user.setDob(userForm.getDob());
//		user.setSsn(userForm.getSsn());
//		save(user);
//	}

	public void editUser(User updatedUser) {
		User user = findById(updatedUser.getId());
		user.setUsername(updatedUser.getUsername());
		user.setPassword(updatedUser.getPassword());
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
