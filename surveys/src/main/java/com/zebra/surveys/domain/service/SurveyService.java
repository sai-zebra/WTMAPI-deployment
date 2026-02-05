package com.zebra.surveys.domain.service;


import com.jayzebra.common.exceptions.ResourceNotFoundException;
import com.zebra.surveys.adapter.output.entity.Survey;
import com.zebra.surveys.adapter.output.entity.SurveyResponse;
import com.zebra.surveys.domain.dto.SurveyCreateDto;
import com.zebra.surveys.domain.dto.SurveyResponseCreateDto;
import com.zebra.surveys.domain.port.input.SurveyUseCase;
import com.zebra.surveys.domain.port.output.SurveyRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 Service - Main logic
 **/
@Service
public class SurveyService implements SurveyUseCase {

    private final SurveyRepositoryPort surveyRepositoryPort;

    public SurveyService(SurveyRepositoryPort surveyRepositoryPort) {
        this.surveyRepositoryPort = surveyRepositoryPort;
    }

    //Function to create new service
    @Override
    public Survey createSurvey(SurveyCreateDto surveyCreateDto) {
        Survey survey = new Survey();
        survey.setId(UUID.randomUUID().toString());
        survey.setTitle(surveyCreateDto.getTitle());
        survey.setQuestions(surveyCreateDto.getQuestions());
        return surveyRepositoryPort.saveSurvey(survey);
    }

    //Function to implement submit survey response
    @Override
    public void submitSurveyResponse(String surveyId, SurveyResponseCreateDto responseDto) {
        // Ensure the survey exists before accepting a response
        surveyRepositoryPort.findSurveyById(surveyId)
                .orElseThrow(() -> new ResourceNotFoundException("Survey not found with id: " + surveyId));

        SurveyResponse response = new SurveyResponse();
        response.setId(UUID.randomUUID().toString());
        response.setSurveyId(surveyId);
        response.setResponses(responseDto.getResponses());
        surveyRepositoryPort.saveSurveyResponse(response);
    }

    //Function to list all surveys
    @Override
    public List<Survey> listSurveys() {
        return surveyRepositoryPort.findAllSurveys();
    }
}

