package com.edmond.bank.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotEmpty(message = "Must select an Account Type")
	@Column(name = "account_type")
	private String accountType;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "User_id", insertable = false, updatable = false)
	private int userId;

	public String lastFourDigitsAcctNumber() {
		accountNumber = this.getAccountNumber();
		return accountNumber.substring(12, 16);

	}

	public Double getAccountBalance() {
		double accountBalance = 0.0;
		for (Transactions transaction : transactionsList) {
			accountBalance += transaction.getAmount();
		}
		return Math.round(accountBalance * 100.0) / 100.0;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "User_id", nullable = false)
	@JsonBackReference
	private User user;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	List<Transactions> transactionsList;

	public Account() {
	}

}
