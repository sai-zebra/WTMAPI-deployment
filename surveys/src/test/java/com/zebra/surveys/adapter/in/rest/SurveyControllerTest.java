package com.zebra.surveys.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zebra.surveys.adapter.input.rest.SurveyController;
import com.zebra.surveys.adapter.output.entity.Survey;
import com.zebra.surveys.domain.dto.SurveyCreateDto;
import com.zebra.surveys.domain.dto.SurveyResponseCreateDto;
import com.zebra.surveys.domain.port.input.SurveyUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration; // <-- IMPORT THIS
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// *** THIS IS THE FIX ***
// We are explicitly excluding the SecurityAutoConfiguration.
// This prevents the security filter chain from blocking our test requests.
@WebMvcTest(controllers = SurveyController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class})
class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SurveyUseCase surveyUseCase;

    @Test
    void createSurvey_should_callUseCaseAndReturn201() throws Exception {
        // ARRANGE
        SurveyCreateDto createDto = new SurveyCreateDto();
        createDto.setTitle("New Survey");
        Survey createdSurvey = new Survey();
        createdSurvey.setId(UUID.randomUUID().toString());
        createdSurvey.setTitle("New Survey");

        when(surveyUseCase.createSurvey(any(SurveyCreateDto.class))).thenReturn(createdSurvey);

        // ACT & ASSERT
        mockMvc.perform(post("/surveys")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(createdSurvey.getId()));

        verify(surveyUseCase).createSurvey(any(SurveyCreateDto.class));
    }

    @Test
    void listSurveys_should_callUseCaseAndReturn200() throws Exception {
        // ARRANGE
        Survey survey1 = new Survey();
        survey1.setId(UUID.randomUUID().toString());
        List<Survey> surveyList = List.of(survey1);

        when(surveyUseCase.listSurveys()).thenReturn(surveyList);

        // ACT & ASSERT
        mockMvc.perform(get("/surveys"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));

        verify(surveyUseCase).listSurveys();
    }

    @Test
    void submitSurveyResponse_should_callUseCaseAndReturn201() throws Exception {
        // ARRANGE
        String surveyId = UUID.randomUUID().toString();
        SurveyResponseCreateDto responseDto = new SurveyResponseCreateDto();
        responseDto.setResponses(Map.of("Q1", "A1"));

        doNothing().when(surveyUseCase).submitSurveyResponse(eq(surveyId), any(SurveyResponseCreateDto.class));

        // ACT & ASSERT
        mockMvc.perform(post("/surveys/{surveyId}/responses", surveyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(responseDto)))
                .andExpect(status().isCreated());

        verify(surveyUseCase).submitSurveyResponse(eq(surveyId), any(SurveyResponseCreateDto.class));
    }
}
