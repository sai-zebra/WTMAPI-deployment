package com.jayzebra.feedsmodule.domain.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Map;

@Component
@Converter(autoApply = false) // We will apply it manually to specific fields
public class FeedsJsonToMapConverter implements AttributeConverter<Map<String, Object>, String> {

    // ObjectMapper is a thread-safe object, so it can be a final field.
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        // Convert Map to JSON String for database storage
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (IllegalArgumentException e) {
            // Log the error or throw a specific exception if conversion fails
            throw new IllegalArgumentException("Error converting map to JSON string for database", e);
        }
    }

    @SneakyThrows
    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        // Convert JSON String from the database back to a Map
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        // Use Map.class to deserialize into a generic Map
        return objectMapper.readValue(dbData, Map.class);
    }
}

