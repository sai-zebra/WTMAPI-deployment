package com.zebra.feedsmodule.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

  //DTO representing  FeedNote list in API responses.
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedNoteResponseDto {
    private UUID id;
    private String message;
    private OffsetDateTime createdAt;
}
