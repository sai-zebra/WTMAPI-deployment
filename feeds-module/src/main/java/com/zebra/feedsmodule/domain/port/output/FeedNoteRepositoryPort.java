package com.zebra.feedsmodule.domain.port.output;

import com.zebra.feedsmodule.domain.model.FeedNote;
import org.springframework.modulith.NamedInterface;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NamedInterface
public interface FeedNoteRepositoryPort {
    //to save feedNote in DB
    FeedNote save(FeedNote feedNote);
    //to delete feedNote when noteId is given
    void deleteById(UUID noteId);
    //to get feedNotes correspond to noteId
    Optional<FeedNote> findById(UUID noteId);
    //to get list of all feedNotes
    List<FeedNote> findByFeedId(UUID feedId);
}
