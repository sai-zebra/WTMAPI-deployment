package com.zebra.surveys.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

//Dto for the surveyResponse
@Getter
@Setter
public class SurveyResponseCreateDto {
    private Map<String, String> responses;

}
