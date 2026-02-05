package com.zebra.usermodule.adapter.in.rest;

import com.zebra.usermodule.adapter.in.UserController;
import com.zebra.usermodule.domain.dto.UserDto;
import com.zebra.usermodule.domain.port.in.UserUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@WithMockUser
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserUseCase userUseCase;

    @Test
    void listUsers_shouldReturnListOfUsers_whenUsersExist() throws Exception {
        UserDto user1 = new UserDto();
        user1.setId("user-1");
        user1.setUsername("johndoe");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("john.doe@example.com");

        UserDto user2 = new UserDto();
        user2.setId("user-2");
        user2.setUsername("janesmith");
        user2.setFirstName("Jane");
        user2.setLastName("Smith");
        user2.setEmail("jane.smith@example.com");

        List<UserDto> users = Arrays.asList(user1, user2);

        when(userUseCase.listUsers()).thenReturn(users);

        // Act & Assert
        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(2)))
                .andExpect(jsonPath("$.users[0].id", is("user-1")))
                .andExpect(jsonPath("$.users[0].username", is("johndoe")))
                .andExpect(jsonPath("$.users[0].firstName", is("John")))
                .andExpect(jsonPath("$.users[1].id", is("user-2")))
                .andExpect(jsonPath("$.users[1].lastName", is("Smith")));
    }

    @Test
    void listUsers_shouldReturnEmptyList_whenNoUsersExist() throws Exception {
        when(userUseCase.listUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users", hasSize(0)));
    }

    @Test
    void getUser_shouldReturnUser_whenUserExists() throws Exception {
        // Arrange
        String userId = "user-123";

        UserDto user = new UserDto();
        user.setId(userId);
        user.setUsername("testuser");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test@example.com");
        user.setProfileImageUrl("https://example.com/profile.png");

        when(userUseCase.getUserById(userId)).thenReturn(Optional.of(user));

        // Act & Assert
        mockMvc.perform(get("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(userId)))
                .andExpect(jsonPath("$.username", is("testuser")))
                .andExpect(jsonPath("$.firstName", is("Test")))
                .andExpect(jsonPath("$.lastName", is("User")))
                .andExpect(jsonPath("$.email", is("test@example.com")))
                .andExpect(jsonPath("$.profileImageUrl", is("https://example.com/profile.png")));
    }

    @Test
    void getUser_shouldReturn404_whenUserDoesNotExist() throws Exception {
        String userId = "non-existent-user";

        when(userUseCase.getUserById(userId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
