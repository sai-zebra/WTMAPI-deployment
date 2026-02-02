package com.jayzebra.feedsmodule.domain.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO representing  FeedNote list in API responses.
 * Based on the FeedNote schema in the OpenAPI specification, using Lombok.
 */


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedNoteResponseDto {
    private UUID id;
    private String message;
    private OffsetDateTime createdAt;
}
