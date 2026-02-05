package com.zebra.feedsmodule.domain.service;


import com.zebra.feedsmodule.domain.model.FeedNote;
import com.zebra.feedsmodule.domain.port.output.FeedNoteRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeedNoteServiceTest {

    @Mock
    private FeedNoteRepositoryPort feedNoteRepositoryPort;

    private FeedNoteService feedNoteService;

    @BeforeEach
    void setUp() {
        feedNoteService = new FeedNoteService(feedNoteRepositoryPort);
    }

    @Test
    void createFeedNote_should_createAndSaveNote() {
        // --- ARRANGE ---
        UUID feedId = UUID.randomUUID();
        String message = "This is a new note.";
        ArgumentCaptor<FeedNote> noteCaptor = ArgumentCaptor.forClass(FeedNote.class);

        // Mock the save operation to return the captured note with a generated ID and timestamp
        when(feedNoteRepositoryPort.save(any(FeedNote.class))).thenAnswer(invocation -> {
            FeedNote noteToSave = invocation.getArgument(0);
            return new FeedNote(UUID.randomUUID(), noteToSave.getFeedId(), noteToSave.getMessage(), OffsetDateTime.now());
        });

        // --- ACT ---
        FeedNote createdNote = feedNoteService.createFeedNote(feedId, message);

        // --- ASSERT ---
        // Verify the save method was called
        verify(feedNoteRepositoryPort).save(noteCaptor.capture());
        FeedNote capturedNote = noteCaptor.getValue();

        // Assert the returned object is not null and has an ID
        assertNotNull(createdNote);
        assertNotNull(createdNote.getId());

        // Assert the object passed to the port was correct before saving
        assertEquals(feedId, capturedNote.getFeedId());
        assertEquals(message, capturedNote.getMessage());
        assertNull(capturedNote.getId(), "ID should be null before saving");
    }

    @Test
    void deleteFeedNote_should_callDeleteByIdOnPort() {
        // --- ARRANGE ---
        UUID feedId = UUID.randomUUID();
        UUID noteId = UUID.randomUUID();

        // --- ACT ---
        feedNoteService.deleteFeedNote(feedId, noteId);

        // --- ASSERT ---
        // Verify that the deleteById method was called with the correct noteId
        verify(feedNoteRepositoryPort, times(1)).deleteById(noteId);
    }

    @Test
    void getNotesForFeed_should_returnListOfNotes() {
        // --- ARRANGE ---
        UUID feedId = UUID.randomUUID();
        List<FeedNote> expectedNotes = List.of(
                new FeedNote(UUID.randomUUID(), feedId, "Note 1", OffsetDateTime.now()),
                new FeedNote(UUID.randomUUID(), feedId, "Note 2", OffsetDateTime.now())
        );

        when(feedNoteRepositoryPort.findByFeedId(feedId)).thenReturn(expectedNotes);

        // --- ACT ---
        List<FeedNote> actualNotes = feedNoteService.getNotesForFeed(feedId);

        // --- ASSERT ---
        assertEquals(2, actualNotes.size());
        assertEquals(expectedNotes, actualNotes);
        verify(feedNoteRepositoryPort, times(1)).findByFeedId(feedId);
    }

    @Test
    void updateFeedNote_should_updateAndSaveChanges() {
        // --- ARRANGE ---
        UUID feedId = UUID.randomUUID();
        UUID noteId = UUID.randomUUID();
        String originalMessage = "Original message";
        String updatedMessage = "This message has been updated.";

        FeedNote existingNote = new FeedNote(noteId, feedId, originalMessage, OffsetDateTime.now());

        // Mock the find and save calls from the port
        when(feedNoteRepositoryPort.findById(noteId)).thenReturn(Optional.of(existingNote));
        when(feedNoteRepositoryPort.save(any(FeedNote.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // --- ACT ---
        FeedNote updatedNote = feedNoteService.updateFeedNote(feedId, noteId, updatedMessage);

        // --- ASSERT ---
        assertNotNull(updatedNote);
        assertEquals(noteId, updatedNote.getId());
        assertEquals(updatedMessage, updatedNote.getMessage(), "The message should be updated.");

        verify(feedNoteRepositoryPort, times(1)).findById(noteId);
        verify(feedNoteRepositoryPort, times(1)).save(existingNote);
    }

    @Test
    void updateFeedNote_should_throwException_whenNoteNotFound() {
        // --- ARRANGE ---
        UUID noteId = UUID.randomUUID();
        when(feedNoteRepositoryPort.findById(noteId)).thenReturn(Optional.empty());

        // --- ACT & ASSERT ---
        assertThrows(RuntimeException.class, () -> {
            feedNoteService.updateFeedNote(UUID.randomUUID(), noteId, "New message");
        });
    }
}

