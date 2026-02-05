package com.zebra.surveys.domain.service;



import com.zebra.common.exceptions.ResourceNotFoundException;
import com.zebra.surveys.adapter.output.entity.Survey;
import com.zebra.surveys.adapter.output.entity.SurveyResponse;
import com.zebra.surveys.domain.dto.SurveyCreateDto;
import com.zebra.surveys.domain.dto.SurveyResponseCreateDto;
import com.zebra.surveys.domain.port.input.SurveyUseCase;
import com.zebra.surveys.domain.port.output.SurveyRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class SurveyService implements SurveyUseCase {

    private final SurveyRepositoryPort surveyRepositoryPort;
    final Logger logger = LoggerFactory.getLogger(SurveyService.class); // Replace YourClassName

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
                .orElseThrow(() ->{
                    logger.error("Survey not found with id: " + surveyId);
                  return new ResourceNotFoundException("Survey not found with id: " + surveyId);
                });

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

