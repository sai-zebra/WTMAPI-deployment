package com.jayzebra.surveys.adapter.input.rest;

import com.jayzebra.surveys.adapter.output.entity.Survey;
import com.jayzebra.surveys.domain.dto.SurveyCreateDto;
import com.jayzebra.surveys.domain.dto.SurveyResponseCreateDto;
import com.jayzebra.surveys.domain.port.input.SurveyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyUseCase surveyUseCase;

    public SurveyController(SurveyUseCase surveyUseCase) {
        this.surveyUseCase = surveyUseCase;
    }

    @PostMapping
    public ResponseEntity<Survey> createSurvey(@RequestBody SurveyCreateDto surveyCreateDto) {
        Survey createdSurvey = surveyUseCase.createSurvey(surveyCreateDto);
        return new ResponseEntity<>(createdSurvey, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Survey>> listSurveys() {
        List<Survey> surveys = surveyUseCase.listSurveys();
        return ResponseEntity.ok(surveys);
    }

    @PostMapping("/{surveyId}/responses")
    public ResponseEntity<Void> submitSurveyResponse(
            @PathVariable String surveyId,
            @RequestBody SurveyResponseCreateDto responseDto) {
        surveyUseCase.submitSurveyResponse(surveyId, responseDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
