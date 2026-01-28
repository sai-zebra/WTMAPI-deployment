//package com.jayzebra.feedsmodule.adapter.in.rest;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.jayzebra.feedsmodule.domain.dto.FeedCreateRequestDto;
//import com.jayzebra.feedsmodule.domain.dto.FeedResponseDto;
//import com.jayzebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
//import com.jayzebra.feedsmodule.domain.port.input.CreateFeedUseCase;
//import com.jayzebra.feedsmodule.domain.port.input.DeleteFeedUseCase;
//import com.jayzebra.feedsmodule.domain.port.input.GetFeedUseCase;
//import com.jayzebra.feedsmodule.domain.port.input.UpdateFeedUseCase;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.UUID;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//// Use @WebMvcTest to test the controller in isolation
//@WebMvcTest(FeedController.class)
//// Since ModelMapper is in the constructor but not used, we might need to provide it as a mock
//// or real bean if Spring can't construct the controller.
//@Import(ModelMapper.class) // Adding this as a real bean is often easiest if it has no dependencies.
//class FeedControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    // Create mocks for all the use case dependencies in the controller
//    @MockBean
//    private CreateFeedUseCase createFeedUseCase;
//    @MockBean
//    private GetFeedUseCase getFeedUseCase;
//    @MockBean
//    private DeleteFeedUseCase deleteFeedUseCase;
//    @MockBean
//    private UpdateFeedUseCase updateFeedUseCase;
//
//    // Note: The ModelMapper bean is provided by the @Import annotation above.
//    // If it were complex, we could use @MockBean for it too.
//
//    private FeedResponseDto feedResponseDto;
//    private UUID feedId;
//
//    @BeforeEach
//    void setUp() {
//        feedId = UUID.randomUUID();
//        feedResponseDto = new FeedResponseDto(); // Assume DTO has fields/setters
//        feedResponseDto.setId(feedId);
//        feedResponseDto.setContent("Test Feed Content");
//    }
//
//    @Test
//    void getAllFeeds_ShouldReturnListOfFeeds() throws Exception {
//        // Arrange: Configure the mock use case to return a list with one feed
//        List<FeedResponseDto> feedList = Collections.singletonList(feedResponseDto);
//        when(getFeedUseCase.getAllFeeds()).thenReturn(feedList);
//
//        // Act: Perform a GET request to the /feeds endpoint
//        ResultActions response = mockMvc.perform(get("/feeds"));
//
//        // Assert: Verify the response and the interaction with the mock
//        response.andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.size()", is(feedList.size())))
//                .andExpect(jsonPath("$[0].id", is(feedId.toString())));
//
//        verify(getFeedUseCase, times(1)).getAllFeeds();
//    }
//
//    @Test
//    void getFeedById_ShouldReturnFeed() throws Exception {
//        // Arrange: Configure the mock use case to return a specific feed
//        when(getFeedUseCase.getFeedById(feedId)).thenReturn(feedResponseDto);
//
//        // Act: Perform a GET request to /feeds/{id}
//        ResultActions response = mockMvc.perform(get("/feeds/{id}", feedId));
//
//        // Assert: Verify the response is correct
//        response.andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(feedId.toString())))
//                .andExpect(jsonPath("$.content", is(feedResponseDto.getContent())));
//
//        verify(getFeedUseCase, times(1)).getFeedById(feedId);
//    }
//
//    @Test
//    void createFeed_ShouldReturnNoContent() throws Exception {
//        // Arrange: Create the request DTO
//        FeedCreateRequestDto createRequestDto = new FeedCreateRequestDto();
//        createRequestDto.setContent("New Feed");
//
//        // For void methods, doNothing().when(...) is explicit, but often unnecessary.
//        // We will use verify() at the end to confirm the call.
//        doNothing().when(createFeedUseCase).createFeed(any(FeedCreateRequestDto.class));
//
//        // Act: Perform a POST request with a JSON body
//        ResultActions response = mockMvc.perform(post("/feeds")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(createRequestDto)));
//
//        // Assert: Verify the status is 204 No Content
//        response.andExpect(status().isNoContent());
//
//        // Verify that the createFeed method on the use case was called once
//        verify(createFeedUseCase, times(1)).createFeed(any(FeedCreateRequestDto.class));
//    }
//
//    @Test
//    void deleteFeed_ShouldReturnNoContent() throws Exception {
//        // Arrange: Mock the void deleteFeed method
//        doNothing().when(deleteFeedUseCase).deleteFeed(feedId);
//
//        // Act: Perform a DELETE request
//        ResultActions response = mockMvc.perform(delete("/feeds/{id}", feedId));
//
//        // Assert: Verify the status is 204 No Content
//        response.andExpect(status().isNoContent());
//
//        // Verify that the deleteFeed method was called once with the correct ID
//        verify(deleteFeedUseCase, times(1)).deleteFeed(feedId);
//    }
//
//    @Test
//    void updateFeed_ShouldReturnNoContent() throws Exception {
//        // Arrange: Create the update request DTO
//        FeedUpdateRequestDto updateRequestDto = new FeedUpdateRequestDto();
//        updateRequestDto.setContent("Updated Content");
//
//        // Mock the void updateFeed method
//        doNothing().when(updateFeedUseCase).updateFeed(any(UUID.class), any(FeedUpdateRequestDto.class));
//
//        // Act: Perform a PATCH request with a JSON body
//        ResultActions response = mockMvc.perform(patch("/feeds/{id}", feedId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(updateRequestDto)));
//
//        // Assert: Verify the status is 204 No Content
//        response.andExpect(status().isNoContent());
//
//        // Verify that the updateFeed method was called once with the correct ID and DTO
//        verify(updateFeedUseCase, times(1)).updateFeed(eq(feedId), any(FeedUpdateRequestDto.class));
//    }
//}
