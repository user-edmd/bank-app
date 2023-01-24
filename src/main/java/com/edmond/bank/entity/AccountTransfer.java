package com.edmond.bank.entity;

import lombok.Data;

@Data
public class AccountTransfer {
	
	private Integer accountIdFrom;
	private Integer accountIdTo;
	private Double amountToTransfer;


}
