package com.edmond.bank.api.UserServiceTests;

import com.edmond.bank.dao.UserRepository;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

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
    }

    @DisplayName("Junit test for deleteUser method")
    @Test
    public void deleteUser() {

        int userId = 1;

        willDoNothing().given(userRepository).deleteById(userId);

        userService.deleteById(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @DisplayName("Junit test for editUser method")
    @Test
    public void editUser() {
        System.out.println(user);
        given(userRepository.save(user)).willReturn(user);
        user.setUsername("ram@gmail.com");
        user.setFirstName("Ram");
        // when -  action or the behaviour that we are going test
        userService.editUser(user);
        System.out.println(user);

        // then - verify the output
        assertThat(user.getUsername()).isEqualTo("ram@gmail.com");
        assertThat(user.getFirstName()).isEqualTo("Ram");
    }

    @DisplayName("JUnit test for getAllUsers method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        // given - precondition or setup

        User user1 = User.builder()
                .id(1)
                .firstName("yvette")
                .lastName("cao")
                .address("456 caminito st")
                .dob("1990-12-12")
                .ssn("532-61-9291")
                .username("ycao@ayhoo.com")
                .build();

        given(userRepository.findAll()).willReturn(List.of(user,user1));

        // when -  action or the behaviour that we are going test
        List<User> employeeList = userService.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for getUserById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        // given
        given(userRepository.findById(1)).willReturn(Optional.of(user));

        // when
        User savedUser = userService.findById(user.getId());

        // then
        assertThat(savedUser).isNotNull();

    }

    @DisplayName("JUnit test for save method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given - precondition or setup
//        given(userRepository.findByUsername(user.getUsername()))
//                .willReturn();

        given(userRepository.save(user)).willReturn(user);

        System.out.println(userRepository);
        System.out.println(userService);

        // when -  action or the behaviour that we are going test
        User savedUser = userService.save(user);

        System.out.println(savedUser);
        // then - verify the output
        assertThat(savedUser).isNotNull();
    }
}
