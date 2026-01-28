package com.jayzebra.rtm.adapter.out.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;

@Component
@Converter(autoApply = false) // We will apply it manually to specific fields
public class JsonToMapConverter implements AttributeConverter<Map<String, Object>, String> {

    // ObjectMapper is a thread-safe object, so it can be a final field.
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        // Convert Map to JSON String for database storage
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (IllegalArgumentException | JsonProcessingException e) {
            // Log the error or throw a specific exception if conversion fails
            throw new IllegalArgumentException("Error converting map to JSON string for database", e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        // Convert JSON String from the database back to a Map
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        // Use Map.class to deserialize into a generic Map
        try {
            return objectMapper.readValue(dbData, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

