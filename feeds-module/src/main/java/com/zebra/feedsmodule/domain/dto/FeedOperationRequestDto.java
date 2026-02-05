package com.zebra.feedsmodule.domain.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
// Data Transfer Object (DTO) representing the JSON request body for performing an action on a feed.
public class FeedOperationRequestDto {
    private FeedOperationType operation;
    // A flexible key-value map for data specific to the operation
    private Map<String, Object> payload;

    public void setOperation(FeedOperationType operation) {
        this.operation = operation;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    // Defines the finite set of all valid operations that can be performed
    public enum FeedOperationType{
        CLAIM, REASSIGN, ACKNOWLEDGE, COMPLETE, ESCALATE
    }
}
