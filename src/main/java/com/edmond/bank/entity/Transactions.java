package com.edmond.bank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "transactions")
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "date")
	private String date;

	@Column(name = "Account_id", insertable = false, updatable = false)
	private int accountId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Account_id", nullable = false)
	@JsonBackReference
	private Account account;

	public Transactions() {
	}

}
