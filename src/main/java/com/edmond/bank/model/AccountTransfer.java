package com.edmond.bank.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountTransfer {

	private int accountIdFrom;
	private int accountIdTo;
	private Double amountToTransfer;

}
