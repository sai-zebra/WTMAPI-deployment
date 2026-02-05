package com.zebra.surveys.adapter.output;

import com.zebra.surveys.adapter.output.entity.Survey;
import com.zebra.surveys.adapter.output.entity.SurveyResponse;
import com.zebra.surveys.adapter.output.repository.SurveyRepository;
import com.zebra.surveys.adapter.output.repository.SurveyResponseRepository;
import com.zebra.surveys.domain.port.output.SurveyRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/**
 * output adaptor
 **/
@Component
public class SurveyRepositoryAdapter implements SurveyRepositoryPort {

    //repository for survey
    private final SurveyRepository surveyRepository;
    //repository for survey response
    private final SurveyResponseRepository surveyResponseRepository;

    //Constructor to inject repositories into the port
    public SurveyRepositoryAdapter(SurveyRepository surveyRepository, SurveyResponseRepository surveyResponseRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyResponseRepository = surveyResponseRepository;
    }

    // to save surveys
    @Override
    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    //To save survey responses
    @Override
    public void saveSurveyResponse(SurveyResponse surveyResponse) {
        surveyResponseRepository.save(surveyResponse);
    }

    //to get surveys by id
    @Override
    public Optional<Survey> findSurveyById(String surveyId) {
        return surveyRepository.findById(surveyId);
    }

    //to get all surveys
    @Override
    public List<Survey> findAllSurveys() {
        return surveyRepository.findAll();
    }
}
