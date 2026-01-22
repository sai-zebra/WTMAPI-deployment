package com.jayzebra.feedsmodule.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedNoteCreateRequestdto {

    @NotBlank(message = "Message cannot be empty.")
    private String message;
}
