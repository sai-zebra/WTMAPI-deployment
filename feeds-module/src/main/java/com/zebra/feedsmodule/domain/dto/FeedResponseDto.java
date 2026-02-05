package com.zebra.feedsmodule.domain.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.modulith.NamedInterface;

import java.time.OffsetDateTime;
import java.util.UUID;

//DTO representing a single Feed item in API responses.
@NamedInterface
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class FeedResponseDto {
    private UUID id;
    private String title;
    private String message;
    private String status;
    private OffsetDateTime createdAt;
}
