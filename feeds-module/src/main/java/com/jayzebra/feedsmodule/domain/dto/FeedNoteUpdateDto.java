package com.jayzebra.feedsmodule.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedNoteUpdateDto {
    @NotBlank(message = "Message cannot be null or blank.")
    private String message;
}
