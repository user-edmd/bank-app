package com.edmond.bank.service;

import java.util.List;

import com.edmond.bank.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

	public Page<User> findAll(Pageable pageable);

	public User findById(int theId);

	public void save(User theUser);

	public void deleteById(int theId);

	public User createUser(User user);

//	public void editUser(int userId, UserForm userForm);
	public void editUser(User user);

	public User findUserByEmail(String email);

}
