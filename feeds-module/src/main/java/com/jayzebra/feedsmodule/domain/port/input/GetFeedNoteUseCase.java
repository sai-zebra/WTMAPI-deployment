package com.jayzebra.feedsmodule.domain.port.input;

import com.jayzebra.feedsmodule.domain.model.FeedNote;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GetFeedNoteUseCase {

    List<FeedNote> getNotesForFeed(UUID feedId);
}
