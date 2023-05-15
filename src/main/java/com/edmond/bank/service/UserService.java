package com.edmond.bank.service;

import java.util.List;

import com.edmond.bank.entity.User;
import com.edmond.bank.model.UserForm;
import org.springframework.stereotype.Service;

public interface UserService {

	public List<User> findAll();

	public User findById(int theId);

	public void save(User theUser);

	public void deleteById(int theId);

	public User createUser(User user);

	public void editUser(int userId, UserForm userForm);

	public User findUserByEmail(String email);

}
