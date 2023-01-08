package com.edmond.bank.entity;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="transactions")
public class Transactions {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="transaction_type")
	private String transactionType;
	
	@Column(name="amount")
	private String amount;
	
	@Column(name="date")
	private String date;
	
	@Column(name="Account_id", insertable=false, updatable=false)
	private int accountId;
	
	public Transactions() { }
	
    @ManyToOne
    @JoinColumn(name="Account_id", nullable=false)
    private Account account;
   
		
}
