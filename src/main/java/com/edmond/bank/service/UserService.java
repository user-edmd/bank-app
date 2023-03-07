package com.edmond.bank.service;

import java.util.List;

import com.edmond.bank.entity.User;
import com.edmond.bank.model.UserForm;

public interface UserService {

	public List<User> findAll();

	public User findById(int theId);

	public void save(User theUser);

	public void deleteById(int theId);

	public void createUser(UserForm userForm);

	public void editUser(int userId, UserForm userForm);

	public User findUserByEmail(String email);

}
