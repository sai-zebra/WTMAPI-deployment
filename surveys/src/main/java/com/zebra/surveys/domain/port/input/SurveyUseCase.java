package com.zebra.surveys.domain.port.input;

/**
 * Input port
 **/
import com.zebra.surveys.adapter.output.entity.Survey;
import com.zebra.surveys.domain.dto.SurveyCreateDto;
import com.zebra.surveys.domain.dto.SurveyResponseCreateDto;
import java.util.List;

public interface SurveyUseCase {
    Survey createSurvey(SurveyCreateDto surveyCreateDto);
    void submitSurveyResponse(String surveyId, SurveyResponseCreateDto responseDto);
    List<Survey> listSurveys();
}

