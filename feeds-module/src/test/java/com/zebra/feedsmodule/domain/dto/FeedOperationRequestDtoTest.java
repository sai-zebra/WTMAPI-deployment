package com.zebra.feedsmodule.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static com.zebra.feedsmodule.domain.dto.FeedOperationRequestDto.FeedOperationType;
import static org.assertj.core.api.Assertions.assertThat;


// Final, robust unit tests for FeedOperationRequestDto.
// This class is designed to kill all PITest mutations for the DTO.

class FeedOperationRequestDtoTest {

    // Basic Getter/Setter and Constructor Tests

    @Test
    @DisplayName("Setters and Getters should work correctly")
    void shouldSetAndGetFields() {
        FeedOperationRequestDto dto = new FeedOperationRequestDto();
        dto.setOperation(FeedOperationType.ACKNOWLEDGE);
        dto.setPayload(Collections.singletonMap("key", "value"));
        assertThat(dto.getOperation()).isEqualTo(FeedOperationType.ACKNOWLEDGE);
        assertThat(dto.getPayload()).containsEntry("key", "value");
    }

    @Test
    @DisplayName("AllArgsConstructor should correctly initialize fields")
    void allArgsConstructorShouldWork() {
        FeedOperationRequestDto dto = new FeedOperationRequestDto(FeedOperationType.CLAIM, Collections.singletonMap("key", "value"));
        assertThat(dto.getOperation()).isEqualTo(FeedOperationType.CLAIM);
        assertThat(dto.getPayload()).containsEntry("key", "value");
    }

    //Tests for equals(), hashCode(), and canEqual() to kill all mutants

    @Test
    @DisplayName("equals should be true for identical objects")
    void equals_shouldBeTrueForIdenticalObjects() {
        FeedOperationRequestDto dto1 = new FeedOperationRequestDto(FeedOperationType.REASSIGN, Collections.singletonMap("id", 1));
        assertThat(dto1.equals(dto1)).isTrue();
    }

    @Test
    @DisplayName("equals should be true for two objects with same values")
    void equals_shouldBeTrueForSameValues() {
        FeedOperationRequestDto dto1 = new FeedOperationRequestDto(FeedOperationType.REASSIGN, Collections.singletonMap("id", 1));
        FeedOperationRequestDto dto2 = new FeedOperationRequestDto(FeedOperationType.REASSIGN, Collections.singletonMap("id", 1));
        assertThat(dto1.equals(dto2)).isTrue();
    }

    @Test
    @DisplayName("equals should be false when comparing to null")
    void equals_shouldBeFalseForNull() {
        FeedOperationRequestDto dto1 = new FeedOperationRequestDto();
        assertThat(dto1.equals(null)).isFalse();
    }

    @Test
    @DisplayName("equals should be false when comparing to different class")
    void equals_shouldBeFalseForDifferentClass() {
        FeedOperationRequestDto dto1 = new FeedOperationRequestDto();
        assertThat(dto1.equals(new Object())).isFalse();
    }

    @Test
    @DisplayName("equals should be false if 'operation' is different")
    void equals_shouldBeFalseForDifferentOperation() {
        FeedOperationRequestDto dto1 = new FeedOperationRequestDto(FeedOperationType.COMPLETE, Collections.singletonMap("id", 1));
        FeedOperationRequestDto dto2 = new FeedOperationRequestDto(FeedOperationType.ESCALATE, Collections.singletonMap("id", 1));
        assertThat(dto1.equals(dto2)).isFalse();
    }

    @Test
    @DisplayName("equals should be false if 'payload' is different")
    void equals_shouldBeFalseForDifferentPayload() {
        FeedOperationRequestDto dto1 = new FeedOperationRequestDto(FeedOperationType.COMPLETE, Collections.singletonMap("id", 1));
        FeedOperationRequestDto dto2 = new FeedOperationRequestDto(FeedOperationType.COMPLETE, Collections.singletonMap("id", 2));
        assertThat(dto1.equals(dto2)).isFalse();
    }

    @Test
    @DisplayName("equals should handle one null field correctly")
    void equals_shouldHandleNullField() {
        FeedOperationRequestDto dto1 = new FeedOperationRequestDto(FeedOperationType.COMPLETE, null);
        FeedOperationRequestDto dto2 = new FeedOperationRequestDto(FeedOperationType.COMPLETE, Collections.singletonMap("id", 2));
        assertThat(dto1.equals(dto2)).isFalse(); // Kills mutant where one field is null
    }

    @Test
    @DisplayName("hashCode should be same for equal objects")
    void hashCode_shouldBeSameForEqualObjects() {
        FeedOperationRequestDto dto1 = new FeedOperationRequestDto(FeedOperationType.REASSIGN, Collections.singletonMap("id", 123));
        FeedOperationRequestDto dto2 = new FeedOperationRequestDto(FeedOperationType.REASSIGN, Collections.singletonMap("id", 123));
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("hashCode should be different for non-equal objects")
    void hashCode_shouldBeDifferentForNonEqualObjects() {
        // This test helps kill the "replaced with 0" and "addition with subtraction" mutants
        FeedOperationRequestDto dto1 = new FeedOperationRequestDto(FeedOperationType.CLAIM, Collections.singletonMap("id", 123));
        FeedOperationRequestDto dto2 = new FeedOperationRequestDto(FeedOperationType.ESCALATE, Collections.singletonMap("id", 456));
        assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
    }

    @Test
    @DisplayName("toString should contain all field values")
    void toString_shouldContainFieldValues() {
        FeedOperationRequestDto dto = new FeedOperationRequestDto(FeedOperationType.ACKNOWLEDGE, Collections.singletonMap("key", "value"));
        assertThat(dto.toString()).contains("operation=ACKNOWLEDGE", "payload={key=value}");
    }
}
