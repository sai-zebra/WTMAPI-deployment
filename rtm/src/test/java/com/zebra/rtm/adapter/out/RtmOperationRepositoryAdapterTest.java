package com.zebra.rtm.adapter.out;

import com.zebra.rtm.adapter.out.entity.RTMEntity;
import com.zebra.rtm.adapter.out.repository.RtmOperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * Unit tests for RtmOperationRepositoryAdapter.
 * This test class verifies that the adapter correctly delegates calls
 * to the underlying RtmOperationRepository, ensuring high coverage.
 */
class RtmOperationRepositoryAdapterTest {

    @Mock
    private RtmOperationRepository rtmOperationRepository; // Mock the actual repository

    @InjectMocks
    private RtmOperationRepositoryAdapter rtmOperationRepositoryAdapter; // Inject mock into the adapter

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should correctly delegate save operation to the underlying repository")
    void save_shouldCallRepositorySave() {
        // Given: A mock RTMEntity instance
        RTMEntity mockRtmEntity = new RTMEntity(); // Create a simple mock entity

        // When: The save method of the adapter is called
        rtmOperationRepositoryAdapter.save(mockRtmEntity);

        // Then: Verify that the save method of the underlying repository was called exactly once with the correct entity
        verify(rtmOperationRepository, times(1)).save(mockRtmEntity);
        // Also verify that no other interactions occurred with the mock repository
        verifyNoMoreInteractions(rtmOperationRepository);
    }
}

