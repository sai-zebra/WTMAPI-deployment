package com.jayzebra.surveys.domain.port.output;

import com.jayzebra.surveys.adapter.output.entity.Survey;
import com.jayzebra.surveys.adapter.output.entity.SurveyResponse;
import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.Optional;

@NamedInterface
public interface SurveyRepositoryPort {
    Survey saveSurvey(Survey survey);
    void saveSurveyResponse(SurveyResponse surveyResponse);
    Optional<Survey> findSurveyById(String surveyId);
    List<Survey> findAllSurveys();
}
