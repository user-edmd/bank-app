package com.edmond.bank.ControllerTests;

import com.edmond.bank.api.UserRestController;
import com.edmond.bank.entity.User;
import com.edmond.bank.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createUser() throws Exception {
        User user = User.builder()
                .id(1)
                .firstName("edmond")
                .lastName("basilan")
                .address("123 test st")
                .dob("1990-06-14")
                .ssn("123-45-6789")
                .username("ebasilan@gmail.com")
                .build();

        mockMvc.perform(post("/api/user").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findUserByIdTest() throws Exception {
        int id = 1;
        User user = User.builder()
                .id(id)
                .firstName("edmond")
                .lastName("basilan")
                .address("123 test st")
                .dob("1990-06-14")
                .ssn("123-45-6789")
                .username("ebasilan@gmail.com")
                .build();

        when(userService.findById(id)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/user/getUserById/{userId}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(user.getLastName()))
                .andExpect(jsonPath("$.address").value(user.getAddress()))
                .andExpect(jsonPath("$.dob").value(user.getDob()))
                .andExpect(jsonPath("$.ssn").value(user.getSsn()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.accountList").value(user.getAccountList()))
                .andDo(print());
    }

    @Test
    void deleteUser() throws Exception {
        int id = 1;

        doNothing().when(userService).deleteById(id);

        mockMvc.perform(delete("/api/user/{userId}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void editUser() throws Exception {
        int id = 1;
        User user = User.builder()
                .id(id)
                .firstName("edmond")
                .lastName("basilan")
                .address("123 test st")
                .dob("1990-06-14")
                .ssn("123-45-6789")
                .username("ebasilan@gmail.com")
                .build();

        User updatedUser = User.builder()
                .id(id)
                .firstName("testFirstName")
                .lastName("testLastName")
                .build();

        when(userService.findById(id)).thenReturn(Optional.of(user));
        when(userService.editUser(any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/user").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}

// JaCoCo