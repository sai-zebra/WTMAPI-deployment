package com.zebra.feedsmodule.domain.port.input;

import com.zebra.feedsmodule.domain.model.FeedNote;
import java.util.UUID;

//input port for updating feedNote
public interface UpdateFeedNoteUseCase {
    FeedNote updateFeedNote(UUID feedId, UUID noteId, String newMessage);
}
