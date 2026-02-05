package com.zebra.usermodule.adapter.in.rest;


import com.zebra.usermodule.adapter.in.UserSessionController;
import com.zebra.usermodule.domain.port.in.UserSessionUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserSessionController.class) // Target the controller
// Run all tests in this class as an authenticated user with a specific username
@WithMockUser(username = "user-test-123")
class UserSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserSessionUseCase userSessionUseCase; // Mock the service layer

    @Test
    void loginSuccess_shouldCallRecordLoginAndReturn200Ok() throws Exception {
        // Arrange
        String expectedUserId = "user-test-123"; // This must match the username in @WithMockUser

        // Act & Assert
        mockMvc.perform(post("/sessions/login")
                        .with(csrf())) //  Add CSRF token for the POST request
                .andExpect(status().isOk()); //  Assert the HTTP 200 OK status

        // Verify
        //  Verify that the use case was called with the correct username from the security context
        verify(userSessionUseCase).recordLogin(expectedUserId);
    }

    @Test
    void logout_shouldCallRecordLogoutAndReturn204NoContent() throws Exception {
        // Arrange
        String expectedUserId = "user-test-123"; // This must match the username in @WithMockUser

        // Act & Assert
        mockMvc.perform(post("/sessions/logout")
                        .with(csrf())) // Add CSRF token for the POST request
                .andExpect(status().isNoContent()); //  Assert the HTTP 204 No Content status

        // Verify
        // Verify that the use case was called with the correct username
        verify(userSessionUseCase).recordLogout(expectedUserId);
    }
}

