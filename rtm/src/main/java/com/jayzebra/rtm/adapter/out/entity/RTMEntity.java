package com.jayzebra.rtm.adapter.out.entity;

// IMPORTANT: This import will fail until you move JsonToMapConverter
// to a shared module (e.g., 'common') or into the 'rtm' module itself.
import com.jayzebra.rtm.adapter.out.module.JsonToMapConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.Map;

@Entity
public class RTMEntity {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private RtmOperationType operation;

    // This tells JPA to use your custom converter for this field
    @Convert(converter = JsonToMapConverter.class)
    // This tells the database to create a text-based column for the JSON
    @Column(columnDefinition = "TEXT")
    private Map<String, Object> payload;

    @Enumerated(EnumType.STRING)
    private OperationStatus status;

    private Instant createdAt;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public RtmOperationType getOperation() { return operation; }
    public void setOperation(RtmOperationType operation) { this.operation = operation; }
    public Map<String, Object> getPayload() { return payload; }
    public void setPayload(Map<String, Object> payload) { this.payload = payload; }
    public OperationStatus getStatus() { return status; }
    public void setStatus(OperationStatus status) { this.status = status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public enum RtmOperationType {
        SEND_SURVEY, BROADCAST, NUDGE, ESCALATE, REASSIGN
    }

    public enum OperationStatus {
        ACCEPTED, PROCESSING, COMPLETED, FAILED
    }
}
