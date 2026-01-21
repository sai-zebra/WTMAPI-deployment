package com.jayzebra.surveys.domain.dto;


import java.util.List;

public class SurveyCreateDto {
    private String title;
    private List<String> questions;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<String> getQuestions() { return questions; }
    public void setQuestions(List<String> questions) { this.questions = questions; }
}

