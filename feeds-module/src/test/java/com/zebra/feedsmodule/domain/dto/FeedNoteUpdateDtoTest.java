package com.zebra.feedsmodule.domain.dto;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


// Unit tests for FeedNoteUpdateDto.
// This class ensures that Lombok-generated methods (equals, hashCode, toString, etc.)
// work as expected, achieving full PITest mutation coverage.

class FeedNoteUpdateDtoTest {

    @Test
    @DisplayName("Getters and Setters should work correctly")
    void shouldSetAndGetFields() {
        // Given
        FeedNoteUpdateDto dto = new FeedNoteUpdateDto();
        String message = "This is an updated note.";

        // When
        dto.setMessage(message);

        // Then
        assertThat(dto.getMessage()).isEqualTo(message);
    }

    @Test
    @DisplayName("AllArgsConstructor should correctly initialize fields")
    void allArgsConstructorShouldWork() {
        // Given
        String message = "Message from constructor.";

        // When
        FeedNoteUpdateDto dto = new FeedNoteUpdateDto(message);

        // Then
        assertThat(dto.getMessage()).isEqualTo(message);
    }

    @Test
    @DisplayName("NoArgsConstructor should create an empty object")
    void noArgsConstructorShouldWork() {
        FeedNoteUpdateDto dto = new FeedNoteUpdateDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getMessage()).isNull();
    }

    @Test
    @DisplayName("equals should return true for two objects with the same values")
    void equalsShouldReturnTrueForSameValues() {
        FeedNoteUpdateDto dto1 = new FeedNoteUpdateDto("Same Message");
        FeedNoteUpdateDto dto2 = new FeedNoteUpdateDto("Same Message");

        assertThat(dto1).isEqualTo(dto2); // This uses .equals()
        assertThat(dto1.equals(dto2)).isTrue();
    }

    @Test
    @DisplayName("equals should return false for two objects with different values")
    void equalsShouldReturnFalseForDifferentValues() {
        FeedNoteUpdateDto dto1 = new FeedNoteUpdateDto("Message A");
        FeedNoteUpdateDto dto2 = new FeedNoteUpdateDto("Message B");

        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    @DisplayName("equals should return false for different object types")
    void equalsShouldReturnFalseForDifferentTypes() {
        FeedNoteUpdateDto dto1 = new FeedNoteUpdateDto("A Message");
        assertThat(dto1.equals(new Object())).isFalse();
    }

    @Test
    @DisplayName("hashCode should be the same for two objects with the same values")
    void hashCodeShouldBeSameForSameValues() {
        FeedNoteUpdateDto dto1 = new FeedNoteUpdateDto("Same Message");
        FeedNoteUpdateDto dto2 = new FeedNoteUpdateDto("Same Message");

        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("hashCode should be different for two objects with different values")
    void hashCodeShouldBeDifferentForDifferentValues() {
        FeedNoteUpdateDto dto1 = new FeedNoteUpdateDto("Message A");
        FeedNoteUpdateDto dto2 = new FeedNoteUpdateDto("Message B");

        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("toString should contain the field name and value")
    void toStringShouldContainFieldValues() {
        FeedNoteUpdateDto dto = new FeedNoteUpdateDto("My Test Update Message");

        String dtoAsString = dto.toString();

        assertThat(dtoAsString).contains("message=My Test Update Message");
    }
}
