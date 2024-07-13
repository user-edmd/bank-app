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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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
                .user(user)
                .build();
    }

    @DisplayName("JUnit test for getAccountById method")
    @Test
    public void getAccountById(){
        // given
        given(accountService.findById(1)).willReturn(Optional.of(account));

        // when
        Optional<Account> savedAccount = accountService.findById(user.getId());

        // then
        assertThat(savedAccount).isNotNull();

    }

    @DisplayName("Junit test for getAllAccounts method")
    @Test
    public void getAllAccounts() {
    // given - precondition or setup

        Account account2 = Account.builder()
                .accountNumber("0000000000000002")
                .accountType("Savings")
                .userId(1)
                .user(user)
                .build();

        given(accountService.findAll()).willReturn(List.of(account, account2));

        // when -  action or the behaviour that we are going test
        List<Account> accountList = accountService.findAll();

        // then - verify the output
        assertThat(accountList).isNotNull();
        assertThat(accountList.size()).isEqualTo(2);
    }

    @DisplayName("Junit test for save Acccount")
    @Test
    public void saveAccountTest() {
        // given - precondition or setup
//        given(userRepository.findByUsername(user.getUsername()))
//                .willReturn();

        given(accountService.save(account)).willReturn(account);

        System.out.println(accountRepository);
        System.out.println(accountService);

        // when -  action or the behaviour that we are going test
        Account savedAccount = accountService.save(account);

        System.out.println(savedAccount);
        // then - verify the output
        assertThat(savedAccount).isNotNull();
    }

    @DisplayName("Junit test for deleteUser method")
    @Test
    public void deleteUser() {

        int accountId = 1;

        willDoNothing().given(accountRepository).deleteById(accountId);

        accountService.deleteById(accountId);

        verify(accountRepository, times(1)).deleteById(accountId);
    }

}


//Page<Account> findAll(Pageable pageable);
//List<Account> findAll();
//Optional<Account> findById(int id);
//Account save(Account account);
//void deleteById(int id);
//Account createAccount(Account account);