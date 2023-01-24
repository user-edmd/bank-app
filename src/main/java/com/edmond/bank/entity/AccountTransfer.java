package com.edmond.bank.entity;

import lombok.Data;

@Data
public class AccountTransfer {
	
	private int accountIdFrom;
	private int accountIdTo;
	private Double amountToTransfer;


}
