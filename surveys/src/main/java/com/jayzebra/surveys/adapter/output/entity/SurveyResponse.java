package com.jayzebra.surveys.adapter.output.entity;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.modulith.NamedInterface;

import java.util.Map;

@NamedInterface
@Entity
public class SurveyResponse {
    @Id
    private String id;
    private String surveyId;
    @ElementCollection
    private Map<String, String> responses;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSurveyId() { return surveyId; }
    public void setSurveyId(String surveyId) { this.surveyId = surveyId; }
    public Map<String, String> getResponses() { return responses; }
    public void setResponses(Map<String, String> responses) { this.responses = responses; }
}
