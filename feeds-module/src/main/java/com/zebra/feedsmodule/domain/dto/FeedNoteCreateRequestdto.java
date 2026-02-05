package com.zebra.feedsmodule.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 //DTO for the POST /feednotes request body
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedNoteCreateRequestdto {

    @NotBlank(message = "Message cannot be empty.")
    private String message;
}
