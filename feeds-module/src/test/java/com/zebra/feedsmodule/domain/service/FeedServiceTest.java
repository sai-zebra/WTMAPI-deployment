package com.zebra.feedsmodule.domain.service;


import com.zebra.feedsmodule.domain.dto.FeedCreateRequestDto;
import com.zebra.feedsmodule.domain.dto.FeedResponseDto;
import com.zebra.feedsmodule.domain.dto.FeedUpdateRequestDto;
import com.zebra.feedsmodule.domain.port.output.FeedRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FeedServiceTest {

    // Mock the output port to isolate the service from the database
    @Mock
    private FeedRepositoryPort feedRepositoryPort;

    // The service class under test
    private FeedService feedService;

    @BeforeEach
    void setUp() {
        // Initialize the service with the mock before each test
        feedService = new FeedService(feedRepositoryPort);
    }

    @Test
    void should_call_port_to_create_feed() {
        // ARRANGE
        FeedCreateRequestDto createDto = new FeedCreateRequestDto("Test Title", "Test Message");

        // ACT
        feedService.createFeed(createDto);

        // ASSERT
        // Verify that the save method on the port was called exactly once with the correct DTO
        verify(feedRepositoryPort, times(1)).save(createDto);
    }

    @Test
    void should_call_port_to_delete_feed() {
        // ARRANGE
        UUID feedId = UUID.randomUUID();

        //ACT
        feedService.deleteFeed(feedId);

        //ASSERT
        // Verify that the deleteFeed method was called with the correct ID
        verify(feedRepositoryPort, times(1)).deleteFeed(feedId);
    }

    @Test
    void should_return_feed_when_getting_by_id() {
        // ARRANGE
        UUID feedId = UUID.randomUUID();
        FeedResponseDto expectedResponse = new FeedResponseDto(feedId, "Title", "Message", "NEW", OffsetDateTime.now());

        // Define the behavior of the mock: when getFeedById is called, return our expected response
        when(feedRepositoryPort.getFeedById(feedId)).thenReturn(expectedResponse);

        //ACT
        FeedResponseDto actualResponse = feedService.getFeedById(feedId);

        //ASSERT
        // Verify the port method was called
        verify(feedRepositoryPort, times(1)).getFeedById(feedId);
        // Assert that the service returned the object it received from the port
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void should_return_all_feeds() {
        //ARRANGE
        List<FeedResponseDto> expectedFeeds = List.of(
                new FeedResponseDto(UUID.randomUUID(), "Feed 1", "Msg 1", "NEW", OffsetDateTime.now()),
                new FeedResponseDto(UUID.randomUUID(), "Feed 2", "Msg 2", "CLAIMED", OffsetDateTime.now())
        );

        // Define the mock behavior
        when(feedRepositoryPort.getAllFeeds()).thenReturn(expectedFeeds);

        //ACT
        List<FeedResponseDto> actualFeeds = feedService.getAllFeeds();

        //ASSERT
        // Verify the port method was called
        verify(feedRepositoryPort, times(1)).getAllFeeds();
        // Assert that the service returned the list it received from the port
        assertEquals(expectedFeeds, actualFeeds);
    }

    @Test
    void should_call_port_to_update_feed() {
        //ARRANGE
        UUID feedId = UUID.randomUUID();
        FeedUpdateRequestDto updateDto = new FeedUpdateRequestDto("Updated Title", "Updated Message");

        //ACT
        feedService.updateFeed(feedId, updateDto);

        //ASSERT
        // Verify that the updateFeed method was called with the correct ID and DTO
        verify(feedRepositoryPort, times(1)).updateFeed(feedId, updateDto);
    }
}

