package com.edmond.bank.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "First Name cannot be empty.")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty.")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Address cannot be empty.")
    @Column(name = "address")
    private String address;

    @NotEmpty(message = "SSN cannot be empty.")
    @Column(name = "ssn")
    private String ssn;

    @Column(name = "dob")
    private String dob;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Account> accountList;

    public User() {
    }

}
