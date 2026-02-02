package com.jayzebra.feedsmodule.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.OffsetDateTime;

/**
 * DTO for the POST /feednotes request body.
 * Based on the FeedNoteCreate schema in the OpenAPI specification, using Lombok.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedNoteCreateRequestdto {

    @NotBlank(message = "Message cannot be empty.")
    private String message;
}
