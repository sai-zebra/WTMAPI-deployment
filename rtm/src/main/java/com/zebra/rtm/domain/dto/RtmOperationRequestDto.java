package com.zebra.rtm.domain.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RtmOperationRequestDto {
    @NotNull
    private RtmOperationType operation;
    @NotNull
    private Map<String, Object> payload;


    // Enum for type safety based on your OpenAPI spec
    public enum RtmOperationType {
        SEND_SURVEY,
        BROADCAST,
        NUDGE,
        ESCALATE,
        REASSIGN
    }
}
