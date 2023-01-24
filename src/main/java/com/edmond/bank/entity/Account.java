package com.edmond.bank.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="User_id", insertable=false, updatable=false)
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
		return accountBalance;
	}
	
    @ManyToOne
    @JoinColumn(name="User_id", nullable=false)
    private User user;
    
	@OneToMany(mappedBy="account")
	List<Transactions> transactionsList;
	
	public Account() { }

}
