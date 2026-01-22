package com.jayzebra.feedsmodule.adapter.out.repository;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FeedNoteRepository extends JpaRepository<FeedNoteEntity, UUID> {
    List<FeedNoteEntity> findByFeedId(UUID feedId);
}
