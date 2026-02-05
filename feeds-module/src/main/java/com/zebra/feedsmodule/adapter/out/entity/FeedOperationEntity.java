package com.zebra.feedsmodule.adapter.out.entity;

import com.zebra.feedsmodule.domain.model.FeedsJsonToMapConverter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Convert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
public class FeedOperationEntity {
    @Id
    private String id;

    // Persist the enum by its name (e.g., "CLAIM", "COMPLETE") instead of by its numeric position
    @Enumerated(EnumType.STRING)
    private FeedOperationType operation;

    //This map is converted to a JSON string for database storage using the {@link FeedsJsonToMapConverter}.
    @Convert(converter = FeedsJsonToMapConverter.class)

    // A TEXT column is used to support potentially large JSON payloads.
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> payload;

//  ensures database values remain stable even if the enum's declaration order changes.
    @Enumerated(EnumType.STRING)
    private FeedOperationStatus status;

    // Automatically set on entity creation and is immutable afterward.
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private OffsetDateTime createdAt;


    //enum class for operation type
    public enum FeedOperationType{
        CLAIM, REASSIGN, ACKNOWLEDGE, COMPLETE, ESCALATE
    }

    //enum class for operation status
    public enum FeedOperationStatus{
        ACCEPTED, PROCESSING, COMPLETED, FAILED
    }
}
