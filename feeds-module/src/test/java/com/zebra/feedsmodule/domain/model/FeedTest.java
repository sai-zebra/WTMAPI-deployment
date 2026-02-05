
package com.zebra.feedsmodule.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


//  Unit tests for the Feed domain model.
//  This class tests the getters and setters to ensure the data object
//  functions correctly and to satisfy PITest mutation coverage.

class FeedTest {

    @Test
    @DisplayName("Should correctly set and get all fields of the Feed domain model")
    void shouldSetAndGetAllFields() {
        // Given
        Feed feed = new Feed();
        UUID expectedId = UUID.randomUUID();
        String expectedTitle = "Domain Feed Title";
        String expectedMessage = "This is a domain message.";
        String expectedStatus = "IN_PROGRESS";
        OffsetDateTime expectedCreatedAt = OffsetDateTime.now();

        // When
        feed.setId(expectedId);
        feed.setTitle(expectedTitle);
        feed.setMessage(expectedMessage);
        feed.setStatus(expectedStatus);
        feed.setCreatedAt(expectedCreatedAt);

        // Then
        // These assertions will fail if the getters are mutated to return incorrect values,
        // thus killing all the NO_COVERAGE mutants.
        assertThat(feed.getId()).isEqualTo(expectedId);
        assertThat(feed.getTitle()).isEqualTo(expectedTitle);
        assertThat(feed.getMessage()).isEqualTo(expectedMessage);
        assertThat(feed.getStatus()).isEqualTo(expectedStatus);
        assertThat(feed.getCreatedAt()).isEqualTo(expectedCreatedAt);
    }
}
