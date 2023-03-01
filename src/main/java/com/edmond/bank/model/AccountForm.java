package com.edmond.bank.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountForm {
    private String accountType;
}
