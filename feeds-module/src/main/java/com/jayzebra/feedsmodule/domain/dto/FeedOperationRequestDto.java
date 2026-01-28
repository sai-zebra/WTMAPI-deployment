package com.jayzebra.feedsmodule.domain.dto;

import com.jayzebra.feedsmodule.adapter.out.entity.FeedOperationEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Data
public class FeedOperationRequestDto {
    private FeedOperationType operation;
    private Map<String, Object> payload;

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

    public enum FeedOperationType{
        CLAIM, REASSIGN, ACKNOWLEDGE, COMPLETE, ESCALATE
    }
}
