package com.zebra.rtm.adapter.out.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the JsonToMapConverter.
 * This test class covers all logical paths in the converter to ensure it behaves
 * as expected and to kill mutations identified by PITest.
 */
class JsonToMapConverterTest {

    private JsonToMapConverter converter;

    @BeforeEach
    void setUp() {
        // Initialize a new converter instance before each test
        converter = new JsonToMapConverter();
    }

    //================================================================================
    // Tests for the convertToDatabaseColumn method (Map -> JSON String)
    //================================================================================

    @Test
    @DisplayName("Should convert a valid Map to its JSON string representation")
    void shouldConvertMapToJsonString() {
        // Given: A map with some data
        Map<String, Object> attribute = new HashMap<>();
        attribute.put("key", "value");
        attribute.put("number", 123);

        // When: The map is converted to a database column
        String dbData = converter.convertToDatabaseColumn(attribute);

        // Then: The result should be a valid JSON string containing the data
        assertThat(dbData).isNotNull();
        assertThat(dbData).contains("\"key\":\"value\"");
        assertThat(dbData).contains("\"number\":123");
    }

    @Test
    @DisplayName("Should return null when a null Map is converted to a database column")
    void shouldReturnNullWhenMapIsNull() {
        // When: A null map is converted
        String dbData = converter.convertToDatabaseColumn(null);

        // Then: The result should be null
        assertThat(dbData).isNull();
    }

    @Test
    @DisplayName("Should convert an empty Map to an empty JSON object string '{}'")
    void shouldConvertEmptyMapToEmptyJsonObject() {
        // When: An empty map is converted
        String dbData = converter.convertToDatabaseColumn(Collections.emptyMap());

        // Then: The result should be an empty JSON object string
        assertThat(dbData).isEqualTo("{}");
    }


    //================================================================================
    // Tests for the convertToEntityAttribute method (JSON String -> Map)
    //================================================================================

    @Test
    @DisplayName("Should convert a valid JSON string back to a Map")
    void shouldConvertJsonStringToMap() {
        // Given: A valid JSON string
        String dbData = "{\"key\":\"value\",\"number\":123}";

        // When: The JSON string is converted to an entity attribute
        Map<String, Object> attribute = converter.convertToEntityAttribute(dbData);

        // Then: The result should be a map containing the correct data
        assertThat(attribute).isNotNull();
        assertThat(attribute).hasSize(2);
        assertThat(attribute).containsEntry("key", "value");
        assertThat(attribute).containsEntry("number", 123); // Jackson handles number types
    }

    @Test
    @DisplayName("Should return null when a null JSON string is converted to an entity attribute")
    void shouldReturnNullWhenDbDataIsNull() {
        // When: A null string is converted
        Map<String, Object> attribute = converter.convertToEntityAttribute(null);

        // Then: The result should be null
        assertThat(attribute).isNull();
    }

    @Test
    @DisplayName("Should return null when an empty JSON string is converted to an entity attribute")
    void shouldReturnNullWhenDbDataIsEmpty() {
        // When: An empty string is converted
        Map<String, Object> attribute = converter.convertToEntityAttribute("");

        // Then: The result should be null
        assertThat(attribute).isNull();
    }

    @Test
    @DisplayName("Should throw a RuntimeException when converting malformed JSON")
    void shouldThrowExceptionForMalformedJson() {
        // Given: A malformed JSON string
        String malformedDbData = "{\"key\":\"value\",";

        // When/Then: Converting it should throw a RuntimeException
        assertThrows(RuntimeException.class, () -> {
            converter.convertToEntityAttribute(malformedDbData);
        });
    }
}

