package com.zebra.rtm.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebra.rtm.domain.dto.RtmOperationRequestDto;
import com.zebra.rtm.domain.port.in.RtmOperationUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections; // <-- Import Collections

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RtmController.class)
class RtmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RtmOperationUseCase rtmOperationUseCase;

    @Test
    void whenValidInput_thenReturns202Accepted() throws Exception {
        // Arrange: Create a VALID request object with NON-NULL values
        RtmOperationRequestDto validRequestDto = new RtmOperationRequestDto(
                RtmOperationRequestDto.RtmOperationType.SEND_SURVEY, // Provide an enum value
                Collections.singletonMap("userId", "user-123") // Provide a non-null payload
        );

        // Act & Assert
        mockMvc.perform(post("/rtm/operations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequestDto))) // Send the valid DTO
                .andExpect(status().isAccepted()); // Assert that the status is 202

        // Verify
        verify(rtmOperationUseCase).performRtmOperation(any(RtmOperationRequestDto.class));
    }

    @Test
    void whenInvalidInput_thenReturns400BadRequest() throws Exception {
        // Arrange: Create an INVALID DTO with null values
        RtmOperationRequestDto invalidRequestDto = new RtmOperationRequestDto(null, null);

        // Act & Assert
        mockMvc.perform(post("/rtm/operations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequestDto)))
                .andExpect(status().isBadRequest()); // Assert that the status is 400

        // Verify
        verify(rtmOperationUseCase, never()).performRtmOperation(any(RtmOperationRequestDto.class));
    }
}
