package com.jayzebra.feedsmodule.adapter;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedEntity;
import com.jayzebra.feedsmodule.adapter.out.entity.FeedNoteEntity;
import com.jayzebra.feedsmodule.adapter.out.repository.FeedNoteRepository;
import com.jayzebra.feedsmodule.domain.model.FeedNote;
import com.jayzebra.feedsmodule.domain.port.output.FeedNoteRepositoryPort;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository

public class FeedNoteRepositoryAdapter implements FeedNoteRepositoryPort {
    private final FeedNoteRepository feedNoteRepository;
    private final ModelMapper modelMapper;

    public FeedNoteRepositoryAdapter(FeedNoteRepository feedNoteRepository, ModelMapper modelMapper) {
        this.feedNoteRepository = feedNoteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public FeedNote save(FeedNote feedNote) {
        // --- MANUAL MAPPING: DOMAIN -> ENTITY ---
        FeedNoteEntity feedNoteEntity = new FeedNoteEntity();
        // ID is handled by @GeneratedValue, so we don't set it for new notes
        if (feedNote.getId() != null) {
            feedNoteEntity.setId(feedNote.getId());
        }
        feedNoteEntity.setMessage(feedNote.getMessage());
        feedNoteEntity.setCreatedAt(feedNote.getCreatedAt());

        FeedEntity parentFeedEntity = new FeedEntity();
        parentFeedEntity.setId(feedNote.getFeedId());
        feedNoteEntity.setFeed(parentFeedEntity);

        FeedNoteEntity savedEntity = feedNoteRepository.save(feedNoteEntity);

        // --- MANUAL MAPPING: ENTITY -> DOMAIN ---
        // Use the public constructor we created to re-hydrate the domain object
        return new FeedNote(
                savedEntity.getId(),
                savedEntity.getFeed().getId(),
                savedEntity.getMessage(),
                savedEntity.getCreatedAt()
        );
    }

    @Override
    public Optional<FeedNote> findById(UUID noteId) {
        return feedNoteRepository.findById(noteId)
                // Manual mapping here as well
                .map(entity -> new FeedNote(
                        entity.getId(),
                        entity.getFeed().getId(),
                        entity.getMessage(),
                        entity.getCreatedAt()
                ));
    }

    @Override
    public List<FeedNote> findByFeedId(UUID feedId) {
        return feedNoteRepository.findByFeedId(feedId).stream()
                // And manual mapping here
                .map(entity -> new FeedNote(
                        entity.getId(),
                        entity.getFeed().getId(),
                        entity.getMessage(),
                        entity.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
    @Override
    public void deleteById(UUID noteId) {
        feedNoteRepository.deleteById(noteId);
    }




}
