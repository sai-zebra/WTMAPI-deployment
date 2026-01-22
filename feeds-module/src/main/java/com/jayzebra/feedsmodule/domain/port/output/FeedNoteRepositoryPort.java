package com.jayzebra.feedsmodule.domain.port.output;

import com.jayzebra.feedsmodule.domain.model.FeedNote;
import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NamedInterface
public interface FeedNoteRepositoryPort {
    FeedNote save(FeedNote feedNote);
    void deleteById(UUID noteId);
    Optional<FeedNote> findById(UUID noteId);
    List<FeedNote> findByFeedId(UUID feedId);
}
