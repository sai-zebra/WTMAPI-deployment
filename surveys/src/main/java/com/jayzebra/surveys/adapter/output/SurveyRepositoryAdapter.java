package com.jayzebra.surveys.adapter.output;

import com.jayzebra.surveys.adapter.output.entity.Survey;
import com.jayzebra.surveys.adapter.output.entity.SurveyResponse;
import com.jayzebra.surveys.adapter.output.repository.SurveyRepository;
import com.jayzebra.surveys.adapter.output.repository.SurveyResponseRepository;
import com.jayzebra.surveys.domain.port.output.SurveyRepositoryPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class SurveyRepositoryAdapter implements SurveyRepositoryPort {

    private final SurveyRepository surveyRepository;
    private final SurveyResponseRepository surveyResponseRepository;

    public SurveyRepositoryAdapter(SurveyRepository surveyRepository, SurveyResponseRepository surveyResponseRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyResponseRepository = surveyResponseRepository;
    }

    @Override
    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public void saveSurveyResponse(SurveyResponse surveyResponse) {
        surveyResponseRepository.save(surveyResponse);
    }

    @Override
    public Optional<Survey> findSurveyById(String surveyId) {
        return surveyRepository.findById(surveyId);
    }

    @Override
    public List<Survey> findAllSurveys() {
        return surveyRepository.findAll();
    }
}
