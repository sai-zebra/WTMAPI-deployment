package com.zebra.feedsmodule.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebra.feedsmodule.domain.dto.FeedOperationRequestDto;
import com.zebra.feedsmodule.domain.port.input.FeedOperationUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// Use @WebMvcTest to test only the web layer and not the full application context
@WebMvcTest(FeedOperationController.class)
class FeedOperationControllerTest {

    // MockMvc allows us to send HTTP requests to our controller without a running server
    @Autowired
    private MockMvc mockMvc;

    // ObjectMapper helps convert our DTO to a JSON string
    @Autowired
    private ObjectMapper objectMapper;

    // We mock the use case (service layer) that the controller depends on
    @MockBean
    private FeedOperationUseCase feedOperationUseCase;

    @Test
    void should_return_202_accepted_when_performing_feed_operation() throws Exception {
        // --- ARRANGE ---
        // 1. Create the request body DTO
        FeedOperationRequestDto requestDto = new FeedOperationRequestDto();
        requestDto.setOperation(FeedOperationRequestDto.FeedOperationType.ACKNOWLEDGE);
        requestDto.setPayload(Map.of("notificationId", "xyz-789"));

        // --- ACT & ASSERT ---
        // 2. Perform a POST request to the endpoint
        mockMvc.perform(post("/feeds/feedId/operations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                // 3. Assert that the response status is 202 Accepted
                .andExpect(status().isAccepted());

        // 4. Verify that the controller correctly called the use case method
        verify(feedOperationUseCase).performFeedOperation(requestDto);
    }
}
