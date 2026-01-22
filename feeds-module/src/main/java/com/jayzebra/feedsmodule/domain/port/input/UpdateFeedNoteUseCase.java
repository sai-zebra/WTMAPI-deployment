package com.jayzebra.feedsmodule.domain.port.input;

import com.jayzebra.feedsmodule.domain.model.FeedNote;

import java.util.UUID;

public interface UpdateFeedNoteUseCase {
    FeedNote updateFeedNote(UUID feedId, UUID noteId, String newMessage);
}
