package com.zebra.feedsmodule.domain.dto;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for FeedCreateRequestDto.
 * This class ensures that Lombok-generated methods (equals, hashCode, toString, etc.)
 * work as expected, achieving full PITest mutation coverage.
 */
class FeedCreateRequestDtoTest {

    @Test
    @DisplayName("Getters and Setters should work correctly")
    void shouldSetAndGetFields() {
        // Given
        FeedCreateRequestDto dto = new FeedCreateRequestDto();
        String title = "New Feed";
        String message = "This is the content of the new feed.";

        // When
        dto.setTitle(title);
        dto.setMessage(message);

        // Then
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getMessage()).isEqualTo(message);
    }

    @Test
    @DisplayName("AllArgsConstructor should correctly initialize fields")
    void allArgsConstructorShouldWork() {
        // Given
        String title = "Constructed Feed";
        String message = "Message from constructor.";

        // When
        FeedCreateRequestDto dto = new FeedCreateRequestDto(title, message);

        // Then
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getMessage()).isEqualTo(message);
    }

    @Test
    @DisplayName("NoArgsConstructor should create an empty object")
    void noArgsConstructorShouldWork() {
        FeedCreateRequestDto dto = new FeedCreateRequestDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getMessage()).isNull();
    }

    @Test
    @DisplayName("equals should return true for two objects with the same values")
    void equalsShouldReturnTrueForSameValues() {
        FeedCreateRequestDto dto1 = new FeedCreateRequestDto("Title", "Message");
        FeedCreateRequestDto dto2 = new FeedCreateRequestDto("Title", "Message");

        assertThat(dto1).isEqualTo(dto2); // This uses .equals()
        assertThat(dto1.equals(dto2)).isTrue();
    }

    @Test
    @DisplayName("equals should return false for two objects with different values")
    void equalsShouldReturnFalseForDifferentValues() {
        FeedCreateRequestDto dto1 = new FeedCreateRequestDto("Title A", "Message A");
        FeedCreateRequestDto dto2 = new FeedCreateRequestDto("Title B", "Message B");

        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    @DisplayName("equals should return false for different object types")
    void equalsShouldReturnFalseForDifferentTypes() {
        FeedCreateRequestDto dto1 = new FeedCreateRequestDto("Title", "Message");
        assertThat(dto1.equals(new Object())).isFalse();
    }

    @Test
    @DisplayName("hashCode should be the same for two objects with the same values")
    void hashCodeShouldBeSameForSameValues() {
        FeedCreateRequestDto dto1 = new FeedCreateRequestDto("Title", "Message");
        FeedCreateRequestDto dto2 = new FeedCreateRequestDto("Title", "Message");

        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("hashCode should be different for two objects with different values")
    void hashCodeShouldBeDifferentForDifferentValues() {
        FeedCreateRequestDto dto1 = new FeedCreateRequestDto("Title A", "Message A");
        FeedCreateRequestDto dto2 = new FeedCreateRequestDto("Title B", "Message B");

        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("toString should contain the field names and values")
    void toStringShouldContainFieldValues() {
        FeedCreateRequestDto dto = new FeedCreateRequestDto("My Title", "My Message");

        String dtoAsString = dto.toString();

        assertThat(dtoAsString).contains("title=My Title");
        assertThat(dtoAsString).contains("message=My Message");
    }
}

