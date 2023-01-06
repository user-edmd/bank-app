package com.edmond.bank.service;

import java.util.List;

import com.edmond.bank.entity.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(int theId);
	
	public void save(User theUser);
	
	public void deleteById(int theId);

}
