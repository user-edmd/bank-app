package com.edmond.bank.entity;

import java.util.List;

import com.edmond.bank.validation.BirthDateValidator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty(message = "First Name cannot be empty.")
	@Column(name = "first_name")
	private String firstName;

	@NotEmpty(message = "Last Name cannot be empty.")
	@Column(name = "last_name")
	private String lastName;

	@NotEmpty(message = "Address cannot be empty.")
	@Column(name = "address")
	private String address;
	@NotEmpty(message = "SSN cannot be empty.")
	@Column(name = "ssn")
	private String ssn;

	@BirthDateValidator
	@NotBlank(message = "Date of Birth cannot be empty.")
	@Column(name = "dob")
	private String dob;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;
//	@OneToMany(mappedBy = "user")
//	List<Account> accountList;

	public User() {
	}

}
