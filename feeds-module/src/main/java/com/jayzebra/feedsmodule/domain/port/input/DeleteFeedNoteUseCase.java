package com.jayzebra.feedsmodule.domain.port.input;

import java.util.UUID;

public interface DeleteFeedNoteUseCase {
    void deleteFeedNote(UUID feedId, UUID noteId);
}
