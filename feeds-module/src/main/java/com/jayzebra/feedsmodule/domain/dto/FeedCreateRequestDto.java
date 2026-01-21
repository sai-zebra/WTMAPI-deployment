package com.jayzebra.feedsmodule.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import org.springframework.modulith.NamedInterface;

/**
 * DTO for the POST /feeds request body.
 * Based on the FeedCreate schema in the OpenAPI specification, using Lombok.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedInterface
public class FeedCreateRequestDto {
    @NotBlank(message = "Title cannot be empty.")
    private String title;

    @NotBlank(message = "Message cannot be empty.")
    private String message;
}
