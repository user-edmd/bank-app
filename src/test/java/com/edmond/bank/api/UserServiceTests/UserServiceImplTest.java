package com.edmond.bank.api.UserServiceTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;

import com.edmond.bank.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.edmond.bank.dao.UserRepository;
import com.edmond.bank.entity.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Page<User> userPage;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .id(1)
                .firstName("edmond")
                .lastName("basilan")
                .address("123 test st")
                .dob("1990-06-14")
                .ssn("123-45-6789")
                .username("ebasilan@gmail.com")
                .build();
        userPage = new PageImpl<>(List.of(user));
    }

    @Test
    public void testFindAllPageable() {
        // Arrange
        PageRequest pageable = PageRequest.of(0, 1);
        when(userRepository.findAll(pageable)).thenReturn(userPage);

        // Act
        Page<User> result = userService.findAll(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testFindById() {
        // Arrange
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.findById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testSave() {
        // Arrange
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User savedUser = userService.save(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());
    }

    @Test
    public void testDeleteById() {
        // Act
        doNothing().when(userRepository).deleteById(1);

        // Assert
        userService.deleteById(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    public void testCreateUser() {
        // Arrange
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User createdUser = userService.createUser(user);

        // Assert
        assertNotNull(createdUser);
        assertEquals(user.getUsername(), createdUser.getUsername());
    }

    @Test
    public void testEditUserWhenUserExists() {
        // Arrange
        user.setFirstName("Tom");
        User editedUser = user;

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(editedUser);

        // Act
        User updatedUser = userService.editUser(editedUser);

        // Assert
        assertNotNull(updatedUser);
        assertEquals(user.getUsername(), updatedUser.getUsername());
        assertNotEquals("edmond", updatedUser.getFirstName());
        assertEquals("Tom", updatedUser.getFirstName());
    }

    @Test
    public void testEditUserWhenUserDoesNotExists() {
        // Arrange
        user.setId(2);
        when(userRepository.findById(2)).thenReturn(Optional.empty());

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.editUser(user));

        // Assert
        assertEquals("User does not exist.", exception.getMessage());
    }

    @Test
    public void testFindUserByEmail() {
        // Arrange
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.findUserByEmail(user.getUsername());

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testFindAll() {
        // Arrange
        when(userRepository.findAll()).thenReturn(List.of(user));

        // Act
        List<User> result = userService.findAll();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}

