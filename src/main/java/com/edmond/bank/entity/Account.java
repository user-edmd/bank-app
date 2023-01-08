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
	
	@Column(name="interest_rate")
	private String interestRate;
	
	@Column(name="User_id", insertable=false, updatable=false)
	private int userId;

	public Account() { }
	
	public String getHiddenInterestRate() {
		interestRate = this.getInterestRate();
		
		return "(..." + interestRate.substring(12, 16) + ")";
		
	}
	
    @ManyToOne
    @JoinColumn(name="User_id", nullable=false)
    private User user;
    
	@OneToMany(mappedBy="account")
	List<Transactions> transactionsList;

}
