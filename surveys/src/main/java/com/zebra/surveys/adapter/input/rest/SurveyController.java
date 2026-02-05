package com.zebra.surveys.adapter.input.rest;

import com.zebra.surveys.adapter.output.entity.Survey;
import com.zebra.surveys.domain.dto.SurveyCreateDto;
import com.zebra.surveys.domain.dto.SurveyResponseCreateDto;
import com.zebra.surveys.domain.port.input.SurveyUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

//Controller to manage the operations related to surveys

@RestController
@RequestMapping("/surveys")
@Tag(name = "Surveys")
public class SurveyController {
   //Input port
    private final SurveyUseCase surveyUseCase;
   //constructor to inject input port
    public SurveyController(SurveyUseCase surveyUseCase) {
        this.surveyUseCase = surveyUseCase;
    }

    //To create new survey
    @PostMapping
    public ResponseEntity<Survey> createSurvey(@RequestBody @Valid SurveyCreateDto surveyCreateDto) {
        Survey createdSurvey = surveyUseCase.createSurvey(surveyCreateDto);
        return new ResponseEntity<>(createdSurvey, HttpStatus.CREATED);
    }

    //To get all the surveys
    @GetMapping
    public ResponseEntity<List<Survey>> listSurveys() {
        //list all surveys from the input port
        List<Survey> surveys = surveyUseCase.listSurveys();
        // return the list
        return ResponseEntity.ok(surveys);
    }

    // to submit the survey by id
    @PostMapping("/{surveyId}/responses")
    public ResponseEntity<Void> submitSurveyResponse(
            @PathVariable String surveyId,
            @RequestBody @Valid SurveyResponseCreateDto responseDto) {
        surveyUseCase.submitSurveyResponse(surveyId, responseDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
