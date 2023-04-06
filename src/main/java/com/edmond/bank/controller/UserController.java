package com.edmond.bank.controller;

import java.util.List;

import com.edmond.bank.model.UserForm;
import com.edmond.bank.service.UserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.edmond.bank.entity.User;
import com.edmond.bank.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users/list")
	public String listUsers(Model theModel) {
		List<User> theUsers = userService.findAll();
		theModel.addAttribute("users", theUsers);
		return "list-users";
	}
	@GetMapping("/users")
	public String users(Model model) {
		List<User> allUsers = userService.findAll();
		model.addAttribute("options", allUsers);
		return "index";
	}
	@GetMapping("/dashboard")
	public String userLogin(Authentication auth, Model model) {
		UserDetailsService userDetailsService = (UserDetailsService) auth.getPrincipal();
		User user = userService.findUserByEmail(userDetailsService.getUsername());
		return "redirect:/user/" + user.getId();
	}
	@GetMapping("/user/{userId}")
	public String retrieveUser(@PathVariable("userId") int userId, Model model, Authentication auth) {
		UserDetailsService userDetailsService = (UserDetailsService) auth.getPrincipal();
		User user = userService.findUserByEmail(userDetailsService.getUsername());
		if (user.getId() != userId) {
			throw new RuntimeException("Unauthorized");
		}
		model.addAttribute("user", user);
		model.addAttribute("userAccounts", user.getAccountList());
		return "user";
	}

	@GetMapping("/user/{userId}/edit")
	public String editUser(@PathVariable("userId") int userId, Model model, Authentication auth) {
		UserDetailsService userDetailsService = (UserDetailsService) auth.getPrincipal();
		User user = userService.findUserByEmail(userDetailsService.getUsername());
		if (user.getId() != userId) {
			throw new RuntimeException("Unauthorized");
		}
		model.addAttribute("user", user);
		return "edit-user";

	}
	@PostMapping("/user/{userId}/edit")
	public String editUserSuccess(@ModelAttribute("user") UserForm userForm, @PathVariable("userId") int userId) {
		userService.editUser(userId, userForm);
		return "redirect:/user/" + userId;
	}
	@GetMapping("/user/{userId}/delete")
	public String deleteUser(@PathVariable("userId") int userId, Model model) {
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		return "delete-user";
	}
	@PostMapping("/user/{userId}/delete")
	public String deleteUserSuccess(@ModelAttribute("user") User user, @PathVariable("userId") int userId) {
		userService.deleteById(userId);
		return "redirect:/";
	}
	@GetMapping("/create-user")
	public String createUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "create-user";
	}

	@PostMapping("/create-user")
	public String createUserSuccess(@Valid @ModelAttribute("user") UserForm userForm, BindingResult result) {
		if (result.hasErrors()) {
			return "create-user";
		}
		userService.createUser(userForm);
		return "redirect:/";
	}

}
