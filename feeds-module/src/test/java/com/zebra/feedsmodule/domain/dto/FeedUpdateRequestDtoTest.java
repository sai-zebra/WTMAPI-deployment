
package com.zebra.feedsmodule.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for FeedUpdateRequestDto.
 * This class ensures that Lombok-generated methods (equals, hashCode, toString, etc.)
 * work as expected, achieving full PITest mutation coverage.
 */
class FeedUpdateRequestDtoTest {

    @Test
    @DisplayName("Getters and Setters should work correctly")
    void shouldSetAndGetFields() {
        // Given
        FeedUpdateRequestDto dto = new FeedUpdateRequestDto();
        String title = "Updated Title";
        String message = "This is the updated content.";

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
        String title = "Constructed Update Title";
        String message = "Update message from constructor.";

        // When
        FeedUpdateRequestDto dto = new FeedUpdateRequestDto(title, message);

        // Then
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getMessage()).isEqualTo(message);
    }

    @Test
    @DisplayName("NoArgsConstructor should create an empty object")
    void noArgsConstructorShouldWork() {
        FeedUpdateRequestDto dto = new FeedUpdateRequestDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getMessage()).isNull();
    }

    @Test
    @DisplayName("equals should return true for two objects with the same values")
    void equalsShouldReturnTrueForSameValues() {
        FeedUpdateRequestDto dto1 = new FeedUpdateRequestDto("Same Title", "Same Message");
        FeedUpdateRequestDto dto2 = new FeedUpdateRequestDto("Same Title", "Same Message");

        assertThat(dto1).isEqualTo(dto2); // This uses .equals()
        assertThat(dto1.equals(dto2)).isTrue();
    }

    @Test
    @DisplayName("equals should return false for two objects with different values")
    void equalsShouldReturnFalseForDifferentValues() {
        FeedUpdateRequestDto dto1 = new FeedUpdateRequestDto("Title A", "Message A");
        FeedUpdateRequestDto dto2 = new FeedUpdateRequestDto("Title B", "Message B");

        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    @DisplayName("equals should return false for different object types")
    void equalsShouldReturnFalseForDifferentTypes() {
        FeedUpdateRequestDto dto1 = new FeedUpdateRequestDto("A Title", "A Message");
        assertThat(dto1.equals(new Object())).isFalse();
    }

    @Test
    @DisplayName("hashCode should be the same for two objects with the same values")
    void hashCodeShouldBeSameForSameValues() {
        FeedUpdateRequestDto dto1 = new FeedUpdateRequestDto("Same Title", "Same Message");
        FeedUpdateRequestDto dto2 = new FeedUpdateRequestDto("Same Title", "Same Message");

        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("hashCode should be different for two objects with different values")
    void hashCodeShouldBeDifferentForDifferentValues() {
        FeedUpdateRequestDto dto1 = new FeedUpdateRequestDto("Title A", "Message A");
        FeedUpdateRequestDto dto2 = new FeedUpdateRequestDto("Title B", "Message B");

        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("toString should contain the field names and values")
    void toStringShouldContainFieldValues() {
        FeedUpdateRequestDto dto = new FeedUpdateRequestDto("My Update Title", "My Update Message");

        String dtoAsString = dto.toString();

        assertThat(dtoAsString).contains("title=My Update Title");
        assertThat(dtoAsString).contains("message=My Update Message");
    }
}
