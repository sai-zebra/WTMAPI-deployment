package com.zebra.surveys.domain.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SurveyCreateDto {
    private String title;
    private List<String> questions;

}

