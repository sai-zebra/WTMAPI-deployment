package com.jayzebra.rtm.domain.dto;
import java.util.Map;

public class RtmOperationRequestDto {
    private RtmOperationType operation;
    private Map<String, Object> payload;

    // Getters and Setters
    public RtmOperationType getOperation() {
        return operation;
    }

    public void setOperation(RtmOperationType operation) {
        this.operation = operation;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    // Enum for type safety based on your OpenAPI spec
    public enum RtmOperationType {
        SEND_SURVEY,
        BROADCAST,
        NUDGE,
        ESCALATE,
        REASSIGN
    }
}
