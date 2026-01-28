package com.jayzebra.feedsmodule.adapter.out.entity;

import com.jayzebra.feedsmodule.domain.model.FeedsJsonToMapConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.Map;

@Entity
@Data
public class FeedOperationEntity {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private FeedOperationType operation;

    @Convert(converter = FeedsJsonToMapConverter.class)

    @Column(columnDefinition = "TEXT")
    private Map<String, Object> payload;

    @Enumerated(EnumType.STRING)
    private FeedOperationStatus status;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private OffsetDateTime createdAt;


    //Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FeedOperationType getOperation() {
        return operation;
    }

    public void setOperation(FeedOperationType operation) {
        this.operation = operation;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public FeedOperationStatus getStatus() {
        return status;
    }

    public void setStatus(FeedOperationStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public enum FeedOperationType{
        CLAIM, REASSIGN, ACKNOWLEDGE, COMPLETE, ESCALATE
    }

    public enum FeedOperationStatus{
        ACCEPTED, PROCESSING, COMPLETED, FAILED
    }
}
