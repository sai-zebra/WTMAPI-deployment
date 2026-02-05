package com.zebra.feedsmodule.domain.service;

import com.zebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import com.zebra.feedsmodule.domain.dto.FeedOperationRequestDto;
import com.zebra.feedsmodule.domain.port.output.FeedOperationRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class FeedOperationServiceTest {

    // Mock the output port, so we don't need a real database
    @Mock
    private FeedOperationRepositoryPort feedOperationRepositoryPort;

    // The class we are testing
    private FeedOperationService feedOperationService;

    @BeforeEach
    void setUp() {
        // Initialize the service with the mocked port before each test
        feedOperationService = new FeedOperationService(feedOperationRepositoryPort);
    }

    @Test
    void should_correctly_build_and_save_feed_operation() {
        // --- ARRANGE ---
        // 1. Create the input DTO for the use case
        FeedOperationRequestDto requestDto = new FeedOperationRequestDto();
        requestDto.setOperation(FeedOperationRequestDto.FeedOperationType.CLAIM);
        requestDto.setPayload(Map.of("userId", 123, "notes", "Claiming this task"));

        // 2. Create an ArgumentCaptor to capture the entity passed to the save method
        ArgumentCaptor<FeedOperationEntity> entityCaptor = ArgumentCaptor.forClass(FeedOperationEntity.class);

        // --- ACT ---
        // 3. Execute the method under test
        feedOperationService.performFeedOperation(requestDto);

        // --- ASSERT ---
        // 4. Verify that the 'save' method on our mocked port was called exactly once
        verify(feedOperationRepositoryPort, times(1)).save(entityCaptor.capture());

        // 5. Get the captured entity that was passed to the save method
        FeedOperationEntity savedEntity = entityCaptor.getValue();

        // 6. Assert that the entity was built correctly by the service
        assertNotNull(savedEntity.getId(), "ID should be generated");
        assertNotNull(savedEntity.getCreatedAt(), "CreatedAt timestamp should be set");
        assertEquals(FeedOperationEntity.FeedOperationStatus.ACCEPTED, savedEntity.getStatus(), "Status should be ACCEPTED");
        assertEquals(FeedOperationEntity.FeedOperationType.CLAIM, savedEntity.getOperation(), "Operation should match the request");
        assertEquals(requestDto.getPayload(), savedEntity.getPayload(), "Payload should match the request");
    }
}

