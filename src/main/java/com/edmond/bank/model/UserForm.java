package com.edmond.bank.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserForm {
    private String firstName;
    private String lastName;
    private String address;
    private String dob;
    private String ssn;
}
