package com.zebra.feedsmodule.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for FeedNoteCreateRequestdto.
 * This class ensures that Lombok-generated methods (equals, hashCode, toString, etc.)
 * work as expected, achieving full PITest mutation coverage.
 */
class FeedNoteCreateRequestdtoTest {

    @Test
    @DisplayName("Getters and Setters should work correctly")
    void shouldSetAndGetFields() {
        // Given
        FeedNoteCreateRequestdto dto = new FeedNoteCreateRequestdto();
        String message = "This is a new note.";

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
        FeedNoteCreateRequestdto dto = new FeedNoteCreateRequestdto(message);

        // Then
        assertThat(dto.getMessage()).isEqualTo(message);
    }

    @Test
    @DisplayName("NoArgsConstructor should create an empty object")
    void noArgsConstructorShouldWork() {
        FeedNoteCreateRequestdto dto = new FeedNoteCreateRequestdto();
        assertThat(dto).isNotNull();
        assertThat(dto.getMessage()).isNull();
    }

    @Test
    @DisplayName("equals should return true for two objects with the same values")
    void equalsShouldReturnTrueForSameValues() {
        FeedNoteCreateRequestdto dto1 = new FeedNoteCreateRequestdto("Same Message");
        FeedNoteCreateRequestdto dto2 = new FeedNoteCreateRequestdto("Same Message");

        assertThat(dto1).isEqualTo(dto2); // This uses .equals()
        assertThat(dto1.equals(dto2)).isTrue();
    }

    @Test
    @DisplayName("equals should return false for two objects with different values")
    void equalsShouldReturnFalseForDifferentValues() {
        FeedNoteCreateRequestdto dto1 = new FeedNoteCreateRequestdto("Message A");
        FeedNoteCreateRequestdto dto2 = new FeedNoteCreateRequestdto("Message B");

        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    @DisplayName("equals should return false for different object types")
    void equalsShouldReturnFalseForDifferentTypes() {
        FeedNoteCreateRequestdto dto1 = new FeedNoteCreateRequestdto("A Message");
        assertThat(dto1.equals(new Object())).isFalse();
    }

    @Test
    @DisplayName("hashCode should be the same for two objects with the same values")
    void hashCodeShouldBeSameForSameValues() {
        FeedNoteCreateRequestdto dto1 = new FeedNoteCreateRequestdto("Same Message");
        FeedNoteCreateRequestdto dto2 = new FeedNoteCreateRequestdto("Same Message");

        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("hashCode should be different for two objects with different values")
    void hashCodeShouldBeDifferentForDifferentValues() {
        FeedNoteCreateRequestdto dto1 = new FeedNoteCreateRequestdto("Message A");
        FeedNoteCreateRequestdto dto2 = new FeedNoteCreateRequestdto("Message B");

        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("toString should contain the field name and value")
    void toStringShouldContainFieldValues() {
        FeedNoteCreateRequestdto dto = new FeedNoteCreateRequestdto("My Test Message");

        String dtoAsString = dto.toString();

        assertThat(dtoAsString).contains("message=My Test Message");
    }
}

