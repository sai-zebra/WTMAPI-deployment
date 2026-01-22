package com.jayzebra.feedsmodule.domain.service;

import com.jayzebra.feedsmodule.domain.model.FeedNote;
import com.jayzebra.feedsmodule.domain.port.input.CreateFeedNoteUseCase;
import com.jayzebra.feedsmodule.domain.port.input.DeleteFeedNoteUseCase;
import com.jayzebra.feedsmodule.domain.port.input.GetFeedNoteUseCase;
import com.jayzebra.feedsmodule.domain.port.input.UpdateFeedNoteUseCase;
import com.jayzebra.feedsmodule.domain.port.output.FeedNoteRepositoryPort;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FeedNoteService implements CreateFeedNoteUseCase, DeleteFeedNoteUseCase, GetFeedNoteUseCase, UpdateFeedNoteUseCase {

    private final FeedNoteRepositoryPort feedNoteRepositoryPort;

    public FeedNoteService(FeedNoteRepositoryPort feedNoteRepositoryPort) {
        this.feedNoteRepositoryPort = feedNoteRepositoryPort;
    }

    @Override
    public FeedNote createFeedNote(UUID feedId, String message) {
        FeedNote newNote = FeedNote.create(feedId, message);
        return feedNoteRepositoryPort.save(newNote);
    }

    @Override
    public void deleteFeedNote(UUID feedId, UUID noteId) {
        feedNoteRepositoryPort.deleteById(noteId);
    }

    @Override
    @Transactional
    public List<FeedNote> getNotesForFeed(UUID feedId) {
        return feedNoteRepositoryPort.findByFeedId(feedId);
    }


    @Override
    public FeedNote updateFeedNote(UUID feedId, UUID noteId, String newMessage) {
        FeedNote existingNote = feedNoteRepositoryPort.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found with ID: " + noteId));
        existingNote.updateMessage(newMessage);
        return feedNoteRepositoryPort.save(existingNote);
    }
}
