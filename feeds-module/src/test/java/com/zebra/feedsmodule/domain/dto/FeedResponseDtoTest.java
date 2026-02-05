
package com.zebra.feedsmodule.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Final, robust unit tests for FeedResponseDto.
 * This class is designed to kill all PITest mutations by testing edge cases
 * in equals(), hashCode(), and other Lombok-generated methods.
 */
class FeedResponseDtoTest {

    // --- Common Test Data ---
    private final UUID id = UUID.randomUUID();
    private final String title = "Test Title";
    private final String message = "Test Message";
    private final String status = "NEW";
    private final OffsetDateTime createdAt = OffsetDateTime.now();

    // --- Basic Constructor and Getter/Setter Tests ---

    @Test
    @DisplayName("AllArgsConstructor should correctly initialize all fields")
    void allArgsConstructor_shouldInitializeAllFields() {
        FeedResponseDto dto = new FeedResponseDto(id, title, message, status, createdAt);
        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getMessage()).isEqualTo(message);
        assertThat(dto.getStatus()).isEqualTo(status);
        assertThat(dto.getCreatedAt()).isEqualTo(createdAt);
    }

    @Test
    @DisplayName("Setters and Getters should work correctly")
    void settersAndGetters_shouldWorkCorrectly() {
        FeedResponseDto dto = new FeedResponseDto();
        dto.setId(id);
        dto.setTitle(title);
        dto.setMessage(message);
        dto.setStatus(status);
        dto.setCreatedAt(createdAt);
        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getMessage()).isEqualTo(message);
        assertThat(dto.getStatus()).isEqualTo(status);
        assertThat(dto.getCreatedAt()).isEqualTo(createdAt);
    }

    // --- Tests for equals(), hashCode(), and canEqual() to Kill All Mutants ---

    @Test
    @DisplayName("equals should be true for identical objects")
    void equals_shouldBeTrueForIdenticalObjects() {
        FeedResponseDto dto1 = new FeedResponseDto(id, title, message, status, createdAt);
        assertThat(dto1.equals(dto1)).isTrue();
    }

    @Test
    @DisplayName("equals should be true for two objects with the same values")
    void equals_shouldBeTrueForSameValues() {
        FeedResponseDto dto1 = new FeedResponseDto(id, title, message, status, createdAt);
        FeedResponseDto dto2 = new FeedResponseDto(id, title, message, status, createdAt);
        assertThat(dto1.equals(dto2)).isTrue();
    }

    @Test
    @DisplayName("equals should be false when comparing with null")
    void equals_shouldBeFalseForNull() {
        FeedResponseDto dto1 = new FeedResponseDto();
        assertThat(dto1.equals(null)).isFalse();
    }

    @Test
    @DisplayName("equals should be false when comparing with a different class")
    void equals_shouldBeFalseForDifferentClass() {
        FeedResponseDto dto1 = new FeedResponseDto();
        assertThat(dto1.equals(new Object())).isFalse();
    }

    @Test
    @DisplayName("equals should be false if any field is different")
    void equals_shouldBeFalseForAnyDifferentField() {
        FeedResponseDto base = new FeedResponseDto(id, title, message, status, createdAt);

        FeedResponseDto diffId = new FeedResponseDto(UUID.randomUUID(), title, message, status, createdAt);
        FeedResponseDto diffTitle = new FeedResponseDto(id, "Diff", message, status, createdAt);
        FeedResponseDto diffMessage = new FeedResponseDto(id, title, "Diff", status, createdAt);
        FeedResponseDto diffStatus = new FeedResponseDto(id, title, message, "Diff", createdAt);
        FeedResponseDto diffCreatedAt = new FeedResponseDto(id, title, message, status, createdAt.minusSeconds(1));

        assertThat(base.equals(diffId)).isFalse();
        assertThat(base.equals(diffTitle)).isFalse();
        assertThat(base.equals(diffMessage)).isFalse();
        assertThat(base.equals(diffStatus)).isFalse();
        assertThat(base.equals(diffCreatedAt)).isFalse();
    }

    @Test
    @DisplayName("equals should handle one null field correctly")
    void equals_shouldHandleNullField() {
        FeedResponseDto dto1 = new FeedResponseDto(id, null, message, status, createdAt);
        FeedResponseDto dto2 = new FeedResponseDto(id, title, message, status, createdAt);
        assertThat(dto1.equals(dto2)).isFalse();
    }

    @Test
    @DisplayName("hashCode should be the same for equal objects")
    void hashCode_shouldBeSameForEqualObjects() {
        FeedResponseDto dto1 = new FeedResponseDto(id, title, message, status, createdAt);
        FeedResponseDto dto2 = new FeedResponseDto(id, title, message, status, createdAt);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("hashCode should ideally be different for non-equal objects")
    void hashCode_shouldBeDifferentForNonEqualObjects() {
        // This test kills math-based mutations (add/subtract, multiply/divide) and the "return 0" mutant.
        FeedResponseDto dto1 = new FeedResponseDto(id, "Title1", "Msg1", "Status1", createdAt);
        FeedResponseDto dto2 = new FeedResponseDto(UUID.randomUUID(), "Title2", "Msg2", "Status2", createdAt.plusDays(1));
        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("toString should contain all field values")
    void toString_shouldContainFieldValues() {
        FeedResponseDto dto = new FeedResponseDto(id, title, message, status, createdAt);
        String dtoAsString = dto.toString();
        assertThat(dtoAsString)
                .contains("id=" + id)
                .contains("title=" + title)
                .contains("message=" + message)
                .contains("status=" + status)
                .contains("createdAt=" + createdAt);
    }
}
