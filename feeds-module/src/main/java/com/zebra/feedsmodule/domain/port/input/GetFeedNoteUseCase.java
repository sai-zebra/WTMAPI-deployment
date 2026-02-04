package com.zebra.feedsmodule.domain.port.input;

import com.zebra.feedsmodule.domain.model.FeedNote;
import java.util.List;
import java.util.UUID;
//input port for listing all feedNotes
public interface GetFeedNoteUseCase {

    List<FeedNote> getNotesForFeed(UUID feedId);
}
