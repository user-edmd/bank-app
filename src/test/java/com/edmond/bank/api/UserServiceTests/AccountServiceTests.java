package com.edmond.bank.api.UserServiceTests;

import com.edmond.bank.dao.AccountRepository;
import com.edmond.bank.dao.UserRepository;
import com.edmond.bank.entity.Account;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.AccountServiceImpl;
import com.edmond.bank.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;

    private User user;

    @BeforeEach
    public void setup() {
        user = User.builder()
                .id(1)
                .firstName("edmond")
                .lastName("basilan")
                .address("123 test st")
                .dob("1990-06-14")
                .ssn("123-45-6789")
                .username("ebasilan@gmail.com")
                .build();

        account = Account.builder()
                .accountNumber("0000000000000001")
                .accountType("Checking")
                .userId(1)
                .build();
    }

    @DisplayName("Junit test for addAccount method")
    @Test
    public void addAccount() {

    }
}
