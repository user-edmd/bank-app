package com.edmond.bank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
	private LocalDateTime date;

	@Column(name = "Account_id", insertable = false, updatable = false)
	private int accountId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "Account_id", nullable = false)
	@JsonBackReference
	private Account account;
}
