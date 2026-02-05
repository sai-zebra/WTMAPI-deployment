package com.zebra.feedsmodule.domain.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the FeedsJsonToMapConverter.
 * This class ensures all conversion logic is tested, including edge cases,
 * to achieve full mutation coverage.
 */
class FeedsJsonToMapConverterTest {

    private FeedsJsonToMapConverter converter;

    @BeforeEach
    void setUp() {
        converter = new FeedsJsonToMapConverter();
    }

    // --- Tests for convertToDatabaseColumn (Map -> JSON) ---

    @Test
    @DisplayName("convertToDatabaseColumn should correctly convert a non-empty Map to a JSON string")
    void convertToDatabaseColumn_shouldConvertMapToJson() {
        // Given
        Map<String, Object> data = new HashMap<>();
        data.put("user", "test");
        data.put("value", 123);

        // When
        String json = converter.convertToDatabaseColumn(data);

        // Then
        assertThat(json).isNotNull();
        assertThat(json).contains("\"user\":\"test\"");
        assertThat(json).contains("\"value\":123");
    }

    @Test
    @DisplayName("convertToDatabaseColumn should return null for a null Map")
    void convertToDatabaseColumn_shouldReturnNullForNullMap() {
        // When
        String json = converter.convertToDatabaseColumn(null);

        // Then
        assertThat(json).isNull();
    }

    @Test
    @DisplayName("convertToDatabaseColumn should convert an empty Map to an empty JSON object string")
    void convertToDatabaseColumn_shouldConvertEmptyMap() {
        // When
        String json = converter.convertToDatabaseColumn(Collections.emptyMap());

        // Then
        assertThat(json).isEqualTo("{}");
    }

    // --- Tests for convertToEntityAttribute (JSON -> Map) ---

    @Test
    @DisplayName("convertToEntityAttribute should correctly convert a valid JSON string to a Map")
    void convertToEntityAttribute_shouldConvertJsonToMap() {
        // Given
        String json = "{\"user\":\"test\",\"value\":123}";

        // When
        Map<String, Object> map = converter.convertToEntityAttribute(json);

        // Then
        assertThat(map).isNotNull();
        assertThat(map).containsEntry("user", "test");
        assertThat(map).containsEntry("value", 123);
    }

    @Test
    @DisplayName("convertToEntityAttribute should return null for a null JSON string")
    void convertToEntityAttribute_shouldReturnNullForNullJson() {
        // When
        Map<String, Object> map = converter.convertToEntityAttribute(null);

        // Then
        assertThat(map).isNull();
    }

    @Test
    @DisplayName("convertToEntityAttribute should return null for an empty JSON string")
    void convertToEntityAttribute_shouldReturnNullForEmptyJson() {
        // When
        Map<String, Object> map = converter.convertToEntityAttribute("");

        // Then
        assertThat(map).isNull();
    }

    @Test
    @DisplayName("convertToEntityAttribute should throw an exception for malformed JSON")
    void convertToEntityAttribute_shouldThrowExceptionForMalformedJson() {
        // Given a broken JSON string
        String malformedJson = "{\"user\":\"test\",";

        // When/Then: Assert that an exception is thrown (due to @SneakyThrows)
        // JsonProcessingException is checked, but @SneakyThrows will wrap it or rethrow it.
        // We can catch the original checked exception type with assertThrows.
        assertThrows(JsonProcessingException.class, () -> {
            converter.convertToEntityAttribute(malformedJson);
        });
    }
}

