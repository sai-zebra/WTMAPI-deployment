package com.jayzebra.feedsmodule.domain.port.input;

import com.jayzebra.feedsmodule.domain.model.FeedNote;

import java.util.UUID;

public interface CreateFeedNoteUseCase {
    FeedNote createFeedNote(UUID feedId, String message);
}
