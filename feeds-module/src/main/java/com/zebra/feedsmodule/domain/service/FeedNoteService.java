package com.zebra.feedsmodule.domain.service;

import com.zebra.common.exceptions.ResourceNotFoundException;
import com.zebra.feedsmodule.domain.model.FeedNote;
import com.zebra.feedsmodule.domain.port.input.CreateFeedNoteUseCase;
import com.zebra.feedsmodule.domain.port.input.DeleteFeedNoteUseCase;
import com.zebra.feedsmodule.domain.port.input.GetFeedNoteUseCase;
import com.zebra.feedsmodule.domain.port.input.UpdateFeedNoteUseCase;
import com.zebra.feedsmodule.domain.port.output.FeedNoteRepositoryPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


//This class implements the use cases for creating, deleting, getting, and updating feed notes.
//It acts as a primary entry point for any feed note related business logic.


@Service
public class FeedNoteService implements CreateFeedNoteUseCase, DeleteFeedNoteUseCase, GetFeedNoteUseCase, UpdateFeedNoteUseCase {

    //output port object
    private final FeedNoteRepositoryPort feedNoteRepositoryPort;
    final Logger logger = LoggerFactory.getLogger(FeedNoteService.class); // Replace YourClassName

    public FeedNoteService(FeedNoteRepositoryPort feedNoteRepositoryPort) {
        this.feedNoteRepositoryPort = feedNoteRepositoryPort;
    }


//      Creates a new feed note.
//      @param feedId The ID of the feed to which this note belongs.
//      @param message The content of the note.
//      @return The newly created FeedNote.

    @Override
    public FeedNote createFeedNote(UUID feedId, String message) {
        FeedNote newNote = FeedNote.create(feedId, message);
        return feedNoteRepositoryPort.save(newNote);
    }

    //Deletes a specific feed note
    @Override
    public void deleteFeedNote(UUID feedId, UUID noteId) {
        feedNoteRepositoryPort.deleteById(noteId);
    }

    //Retrieves all notes associated with a specific feed.
    //@Transactional annotation ensures that this operation is executed within a database transaction.
    @Override
    @Transactional
    public List<FeedNote> getNotesForFeed(UUID feedId) {
        return feedNoteRepositoryPort.findByFeedId(feedId);
    }


    //Updates the message of an existing feed note.
    @Override
    public FeedNote updateFeedNote(UUID feedId, UUID noteId, String newMessage) {
        FeedNote existingNote = feedNoteRepositoryPort.findById(noteId)
                // If the note is not found, a RuntimeException is thrown
                .orElseThrow(() ->{
                    logger.error("Feed not found with id "+feedId);
                   return new ResourceNotFoundException("Note not found with ID: " + noteId);
                });
        existingNote.updateMessage(newMessage);
        // The updated note is saved back to the repository.
        return feedNoteRepositoryPort.save(existingNote);
    }
}
