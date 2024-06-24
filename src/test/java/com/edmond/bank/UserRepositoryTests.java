package com.edmond.bank;

import com.edmond.bank.dao.UserRepository;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.UserService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepositoryTestsMethod() {
        //Arrange
        User user = User.builder()
                .firstName("edmond")
                .lastName("basilan")
                .address("123 test st")
                .dob("1990-06-14")
                .ssn("123-45-6789")
                .username("ebasilan@gmail.com")
                .build();
        //Act
        User savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getId());
        Optional<User> userReturn = userRepository.findById(savedUser.getId());


        //Assert
        Assertions.assertThat(userReturn).isEmpty();

    }

}
