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
                        .with(csrf())) // Adding CSRF token for the POST request
                .andExpect(status().isOk()); // Asserting the HTTP 200 OK status

        // Verify
        // Verifying that the use case was called with the correct username from the security context
        verify(userSessionUseCase).recordLogin(expectedUserId);
    }

    @Test
    void logout_shouldCallRecordLogoutAndReturn204NoContent() throws Exception {
        // Arrange
        String expectedUserId = "user-test-123"; // This must match the username in @WithMockUser

        // Act & Assert
        mockMvc.perform(post("/sessions/logout")
                        .with(csrf())) // Add CSRF token for the POST request
                .andExpect(status().isNoContent()); // Asserting the HTTP 204 No Content status

        // Verify
        // Verifying that the use case was called with the correct username
        verify(userSessionUseCase).recordLogout(expectedUserId);
    }
}

