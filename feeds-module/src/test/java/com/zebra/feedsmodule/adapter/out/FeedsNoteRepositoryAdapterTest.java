// Location: feeds-module/src/test/java/com/jayzebra/feedsmodule/adapter/out/FeedsNoteRepositoryAdapterTest.java
// THIS IS THE CORRECTED FILE. PLEASE REPLACE THE OLD ONE.

package com.zebra.feedsmodule.adapter.out;

import com.zebra.feedsmodule.adapter.out.entity.FeedEntity;
import com.zebra.feedsmodule.adapter.out.entity.FeedNoteEntity;
import com.zebra.feedsmodule.adapter.out.repository.FeedNoteRepository;
import com.zebra.feedsmodule.domain.model.FeedNote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.OffsetDateTime; // <-- CORRECT IMPORT
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class FeedsNoteRepositoryAdapterTest {

    @Mock
    private FeedNoteRepository feedNoteRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FeedNoteRepositoryAdapter feedNoteRepositoryAdapter;

    private UUID feedId;
    private UUID noteId;
    private OffsetDateTime now; // <-- CORRECT TYPE

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        feedId = UUID.randomUUID();
        noteId = UUID.randomUUID();
        now = OffsetDateTime.now(); // <-- CORRECT INITIALIZATION
    }

    // Helper method for creating test FeedNoteEntity instances
    private FeedNoteEntity createMockFeedNoteEntity(UUID noteId, UUID feedId, String message, OffsetDateTime createdAt) { // <-- CORRECT TYPE
        FeedEntity feed = new FeedEntity();
        feed.setId(feedId);

        FeedNoteEntity note = new FeedNoteEntity();
        note.setId(noteId);
        note.setFeed(feed);
        note.setMessage(message);
        note.setCreatedAt(createdAt); // This now accepts OffsetDateTime
        return note;
    }

    @Test
    @DisplayName("save should correctly map from domain to entity and back for a new note (null ID)")
    void save_forNewNote_shouldMapAndDelegate() {
        // Given a domain object for a new note
        FeedNote newDomainNote = new FeedNote(null, feedId, "New note message", now);

        // Mock the entity that the repository will return
        FeedNoteEntity savedEntityFromRepo = createMockFeedNoteEntity(noteId, feedId, "New note message", now);
        when(feedNoteRepository.save(any(FeedNoteEntity.class))).thenReturn(savedEntityFromRepo);

        // When
        FeedNote resultDomainNote = feedNoteRepositoryAdapter.save(newDomainNote);

        // Then verify mapping from domain to entity before saving
        ArgumentCaptor<FeedNoteEntity> entityCaptor = ArgumentCaptor.forClass(FeedNoteEntity.class);
        verify(feedNoteRepository, times(1)).save(entityCaptor.capture());

        FeedNoteEntity capturedEntity = entityCaptor.getValue();
        assertThat(capturedEntity.getId()).isNull();
        assertThat(capturedEntity.getMessage()).isEqualTo("New note message");
        assertThat(capturedEntity.getCreatedAt()).isEqualTo(now);
        assertThat(capturedEntity.getFeed()).isNotNull();
        assertThat(capturedEntity.getFeed().getId()).isEqualTo(feedId);

        // Then verify mapping from entity back to domain object after saving
        assertThat(resultDomainNote).isNotNull();
        assertThat(resultDomainNote.getId()).isEqualTo(noteId);
    }

    @Test
    @DisplayName("save should correctly map from domain to entity and back for an existing note (with ID)")
    void save_forExistingNote_shouldMapAndDelegate() {
        // Given
        FeedNote existingDomainNote = new FeedNote(noteId, feedId, "Updated note message", now);

        FeedNoteEntity savedEntityFromRepo = createMockFeedNoteEntity(noteId, feedId, "Updated note message", now);
        when(feedNoteRepository.save(any(FeedNoteEntity.class))).thenReturn(savedEntityFromRepo);

        // When
        feedNoteRepositoryAdapter.save(existingDomainNote);

        // Then
        ArgumentCaptor<FeedNoteEntity> entityCaptor = ArgumentCaptor.forClass(FeedNoteEntity.class);
        verify(feedNoteRepository, times(1)).save(entityCaptor.capture());
        assertThat(entityCaptor.getValue().getId()).isEqualTo(noteId);
    }

    @Test
    @DisplayName("findById should return mapped domain object when entity exists")
    void findById_whenFound_shouldReturnMappedOptional() {
        // Given
        FeedNoteEntity foundEntity = createMockFeedNoteEntity(noteId, feedId, "Found note message", now);
        when(feedNoteRepository.findById(noteId)).thenReturn(Optional.of(foundEntity));

        // When
        Optional<FeedNote> result = feedNoteRepositoryAdapter.findById(noteId);

        // Then
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(noteId);
        assertThat(result.get().getMessage()).isEqualTo("Found note message");
    }

    @Test
    @DisplayName("findById should return empty optional when entity does not exist")
    void findById_whenNotFound_shouldReturnEmptyOptional() {
        // Given
        when(feedNoteRepository.findById(noteId)).thenReturn(Optional.empty());

        // When
        Optional<FeedNote> result = feedNoteRepositoryAdapter.findById(noteId);

        // Then
        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("findByFeedId should return a list of mapped domain objects when entities are found")
    void findByFeedId_whenFound_shouldReturnMappedList() {
        // Given
        FeedNoteEntity entity1 = createMockFeedNoteEntity(UUID.randomUUID(), feedId, "Msg 1", now);
        FeedNoteEntity entity2 = createMockFeedNoteEntity(UUID.randomUUID(), feedId, "Msg 2", now.plusHours(1));
        when(feedNoteRepository.findByFeedId(feedId)).thenReturn(List.of(entity1, entity2));

        // When
        List<FeedNote> results = feedNoteRepositoryAdapter.findByFeedId(feedId);

        // Then
        assertThat(results).hasSize(2);
        assertThat(results.get(0).getMessage()).isEqualTo("Msg 1");
        assertThat(results.get(1).getMessage()).isEqualTo("Msg 2");
    }

    @Test
    @DisplayName("findByFeedId should return an empty list when no entities are found")
    void findByFeedId_whenNotFound_shouldReturnEmptyList() {
        // Given
        when(feedNoteRepository.findByFeedId(feedId)).thenReturn(Collections.emptyList());

        // When
        List<FeedNote> results = feedNoteRepositoryAdapter.findByFeedId(feedId);

        // Then
        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("deleteById should delegate the call to the repository")
    void deleteById_shouldDelegateCall() {
        // When
        feedNoteRepositoryAdapter.deleteById(noteId);

        // Then
        verify(feedNoteRepository, times(1)).deleteById(noteId);
    }
}
