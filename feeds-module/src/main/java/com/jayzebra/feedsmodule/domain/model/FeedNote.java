package com.jayzebra.feedsmodule.domain.model;

import lombok.Data;
import lombok.Getter;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Getter
@Data
public final class FeedNote {

    private final UUID id;
    private final UUID feedId;
    private String message;
    private final OffsetDateTime createdAt;

    public FeedNote(UUID id, UUID feedId, String message, OffsetDateTime createdAt) {
        this.id = id;
        this.feedId = feedId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public static FeedNote create(UUID feedId, String message) {
        if (feedId == null) throw new IllegalArgumentException("Feed ID cannot be null.");
        if (message == null || message.isBlank()) throw new IllegalArgumentException("Message cannot be blank.");

        return new FeedNote(null, feedId, message, OffsetDateTime.now(ZoneOffset.UTC));
    }

    public void updateMessage(String newMessage) {
        if (newMessage == null || newMessage.isBlank()) {
            throw new IllegalArgumentException("Cannot update message to be blank.");
        }
        this.message = newMessage;
    }
}
