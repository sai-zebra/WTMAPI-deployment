package com.jayzebra.feedsmodule.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

//DTO for requesting feedNote update
@Data
@NoArgsConstructor
@AllArgsConstructor

public class FeedNoteUpdateDto {

    //Validation to ensure message shouldn't be empty
    @NotBlank(message = "Message cannot be null or blank.")
    private String message;
}
