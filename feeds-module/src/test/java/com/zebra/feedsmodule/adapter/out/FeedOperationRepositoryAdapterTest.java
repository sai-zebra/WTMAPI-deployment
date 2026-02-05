package com.zebra.feedsmodule.adapter.out;

import com.zebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import com.zebra.feedsmodule.adapter.out.repository.FeedOperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for FeedOperationRepositoryAdapter.
 * This class ensures that the adapter correctly delegates the save operation
 * to the underlying FeedOperationRepository, achieving full mutation coverage.
 */
class FeedOperationRepositoryAdapterTest {

    @Mock
    private FeedOperationRepository feedOperationRepository; // Mock the actual repository

    @InjectMocks
    private FeedOperationRepositoryAdapter feedOperationRepositoryAdapter; // Inject mock into the adapter

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("save should correctly delegate the operation to the underlying repository")
    void save_shouldCallRepositorySave() {
        // Given: A mock FeedOperationEntity instance
        FeedOperationEntity mockFeedOperationEntity = new FeedOperationEntity();
        mockFeedOperationEntity.setId("op-123"); // Assuming an ID for completeness

        // Define the behavior of the mock repository: it should return the entity it was given
        when(feedOperationRepository.save(mockFeedOperationEntity)).thenReturn(mockFeedOperationEntity);

        // When: The save method of the adapter is called
        FeedOperationEntity savedEntity = feedOperationRepositoryAdapter.save(mockFeedOperationEntity);

        // Then:
        // 1. Verify that the save method of the underlying repository was called exactly once with the correct entity
        verify(feedOperationRepository, times(1)).save(mockFeedOperationEntity);

        // 2. Assert that the adapter returned the same entity that the repository returned
        assertThat(savedEntity).isEqualTo(mockFeedOperationEntity);

        // 3. Verify that no other interactions occurred with the mock repository
        verifyNoMoreInteractions(feedOperationRepository);
    }
}
