package com.jayzebra.surveys.domain.port.input;

// SurveyUseCase.java
import com.jayzebra.surveys.adapter.output.entity.Survey;
import com.jayzebra.surveys.domain.dto.SurveyCreateDto;
import com.jayzebra.surveys.domain.dto.SurveyResponseCreateDto;

import java.util.List;

public interface SurveyUseCase {
    Survey createSurvey(SurveyCreateDto surveyCreateDto);
    void submitSurveyResponse(String surveyId, SurveyResponseCreateDto responseDto);
    List<Survey> listSurveys();
}

