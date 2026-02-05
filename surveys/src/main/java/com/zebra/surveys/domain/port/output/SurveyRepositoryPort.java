package com.zebra.surveys.domain.port.output;

import com.zebra.surveys.adapter.output.entity.Survey;
import com.zebra.surveys.adapter.output.entity.SurveyResponse;
import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.Optional;

/**
 * Output port
 **/
@NamedInterface
public interface SurveyRepositoryPort {
    Survey saveSurvey(Survey survey);
    void saveSurveyResponse(SurveyResponse surveyResponse);
    Optional<Survey> findSurveyById(String surveyId);
    List<Survey> findAllSurveys();
}
