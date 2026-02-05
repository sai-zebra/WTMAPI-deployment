package com.zebra.rtm.adapter.out.entity;

// IMPORTANT: This import will fail until you move JsonToMapConverter
// to a shared module (e.g., 'common') or into the 'rtm' module itself.
import com.zebra.rtm.adapter.out.model.JsonToMapConverter;

import jakarta.persistence.Convert;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Entity
public class RTMEntity {

    // Getters and Setters
    @Id
    @NotBlank
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

    public enum RtmOperationType {
        SEND_SURVEY, BROADCAST, NUDGE, ESCALATE, REASSIGN
    }

    public enum OperationStatus {
        ACCEPTED, PROCESSING, COMPLETED, FAILED
    }
}
