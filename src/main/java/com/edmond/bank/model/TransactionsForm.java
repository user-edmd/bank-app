package com.edmond.bank.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionsForm {
    private String transactionType;
    private String amount;

}
