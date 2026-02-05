package com.zebra.feedsmodule.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


// Unit tests for FeedNoteResponseDto.
// This class ensures Lombok-generated methods (equals, hashCode, toString, etc.)
// work as expected, killing all PITest mutations.

class FeedNoteResponseDtoTest {

    // Common test data
    private final UUID id = UUID.randomUUID();
    private final String message = "Test Note Message";
    private final OffsetDateTime createdAt = OffsetDateTime.now();

    // Helper to create a consistent DTO for tests
    private FeedNoteResponseDto createDto() {
        return new FeedNoteResponseDto(id, message, createdAt);
    }

    @Test
    @DisplayName("Setters and Getters should work correctly")
    void settersAndGetters_shouldWorkCorrectly() {
        FeedNoteResponseDto dto = new FeedNoteResponseDto();
        dto.setId(id);
        dto.setMessage(message);
        dto.setCreatedAt(createdAt);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getMessage()).isEqualTo(message);
        assertThat(dto.getCreatedAt()).isEqualTo(createdAt);
    }

    @Test
    @DisplayName("AllArgsConstructor should correctly initialize all fields")
    void allArgsConstructor_shouldInitializeAllFields() {
        FeedNoteResponseDto dto = new FeedNoteResponseDto(id, message, createdAt);
        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getMessage()).isEqualTo(message);
        assertThat(dto.getCreatedAt()).isEqualTo(createdAt);
    }

    @Test
    @DisplayName("NoArgsConstructor should create an empty DTO")
    void noArgsConstructor_shouldCreateEmptyDto() {
        FeedNoteResponseDto dto = new FeedNoteResponseDto();
        assertThat(dto.getId()).isNull();
    }

    @Test
    @DisplayName("equals should return true for two objects with the same values")
    void equals_shouldReturnTrueForSameValues() {
        FeedNoteResponseDto dto1 = createDto();
        FeedNoteResponseDto dto2 = createDto();
        assertThat(dto1).isEqualTo(dto2); // This uses .equals()
    }

    // NEW TESTS TO KILL SURVIVORS

    @Test
    @DisplayName("equals should return false when comparing with null")
    void equals_shouldReturnFalseForNull() {
        FeedNoteResponseDto dto1 = createDto();
        // This test kills mutations that break the 'if (o == null)' check.
        assertThat(dto1.equals(null)).isFalse();
    }

    @Test
    @DisplayName("equals should return false when comparing with a different object type")
    void equals_shouldReturnFalseForDifferentType() {
        FeedNoteResponseDto dto1 = createDto();
        // This test kills mutations that break the 'instanceof' or 'canEqual' checks.
        assertThat(dto1.equals(new Object())).isFalse();
    }

    @Test
    @DisplayName("equals should return false for DTOs with different 'message' values")
    void equals_shouldReturnFalseForDifferentMessage() {
        FeedNoteResponseDto dto1 = createDto();
        FeedNoteResponseDto dto2 = new FeedNoteResponseDto(id, "A different message", createdAt);
        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    @DisplayName("hashCode should be different for two objects with different values")
    void hashCode_shouldBeDifferentForDifferentValues() {
        FeedNoteResponseDto dto1 = createDto();
        FeedNoteResponseDto dto2 = new FeedNoteResponseDto(UUID.randomUUID(), "Different Message", OffsetDateTime.now().minusDays(1));
        // This test kills the mutant that changes hashCode() to always return 0.
        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }

    // END OF NEW TESTS

    @Test
    @DisplayName("equals should return false for DTOs with different IDs")
    void equals_shouldReturnFalseForDifferentIds() {
        FeedNoteResponseDto dto1 = createDto();
        FeedNoteResponseDto dto2 = new FeedNoteResponseDto(UUID.randomUUID(), message, createdAt);
        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    @DisplayName("hashCode should be the same for two objects with the same values")
    void hashCode_shouldBeSameForSameValues() {
        FeedNoteResponseDto dto1 = createDto();
        FeedNoteResponseDto dto2 = createDto();
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("toString should contain all field names and values")
    void toString_shouldContainFieldValues() {
        FeedNoteResponseDto dto = createDto();
        String dtoAsString = dto.toString();
        assertThat(dtoAsString).contains("id=" + id);
        assertThat(dtoAsString).contains("message=" + message);
        assertThat(dtoAsString).contains("createdAt=" + createdAt);
    }
}
