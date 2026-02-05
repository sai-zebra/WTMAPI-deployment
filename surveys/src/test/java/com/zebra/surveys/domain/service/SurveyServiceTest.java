package com.zebra.surveys.domain.service;

import com.zebra.common.exceptions.ResourceNotFoundException;
import com.zebra.surveys.adapter.output.entity.Survey;
import com.zebra.surveys.adapter.output.entity.SurveyResponse;
import com.zebra.surveys.domain.dto.SurveyCreateDto;
import com.zebra.surveys.domain.dto.SurveyResponseCreateDto;
import com.zebra.surveys.domain.port.output.SurveyRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * This test class is designed to achieve approximately 80-90% coverage for SurveyService.
 * It intentionally omits the test for the survey-not-found error path in submitSurveyResponse.
 */
@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {

    @Mock
    private SurveyRepositoryPort surveyRepositoryPort;

    private SurveyService surveyService;

    @BeforeEach
    void setUp() {
        surveyService = new SurveyService(surveyRepositoryPort);
    }

    @Test
    @DisplayName("Should correctly build and save a new survey")
    void createSurvey_should_buildAndSaveSurvey() {
        // --- ARRANGE ---
        SurveyCreateDto createDto = new SurveyCreateDto();
        createDto.setTitle("Customer Feedback");
        createDto.setQuestions(List.of("How was your experience?", "Would you recommend us?"));

        // Mock the port to return the saved entity
        when(surveyRepositoryPort.saveSurvey(any(Survey.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // --- ACT ---
        Survey createdSurvey = surveyService.createSurvey(createDto);

        // --- ASSERT ---
        assertNotNull(createdSurvey);
        assertNotNull(createdSurvey.getId(), "A UUID ID should be generated");
        assertEquals("Customer Feedback", createdSurvey.getTitle());
        assertEquals(2, createdSurvey.getQuestions().size());

        verify(surveyRepositoryPort, times(1)).saveSurvey(any(Survey.class));
    }

    @Test
    @DisplayName("Should return a list of all surveys from the port")
    void listSurveys_should_returnAllSurveys() {
        // --- ARRANGE ---
        Survey survey1 = new Survey();
        survey1.setId(UUID.randomUUID().toString());
        survey1.setTitle("Survey 1");
        List<Survey> expectedSurveys = List.of(survey1);

        when(surveyRepositoryPort.findAllSurveys()).thenReturn(expectedSurveys);

        // --- ACT ---
        List<Survey> actualSurveys = surveyService.listSurveys();

        // --- ASSERT ---
        assertEquals(1, actualSurveys.size());
        assertEquals("Survey 1", actualSurveys.get(0).getTitle());
        verify(surveyRepositoryPort, times(1)).findAllSurveys();
    }

    @Test
    @DisplayName("Should save a survey response when the survey exists (Happy Path)")
    void submitSurveyResponse_should_saveResponse_whenSurveyExists() {
        // --- ARRANGE ---
        String surveyId = UUID.randomUUID().toString();
        Survey existingSurvey = new Survey();
        existingSurvey.setId(surveyId);

        SurveyResponseCreateDto responseDto = new SurveyResponseCreateDto();
        responseDto.setResponses(Map.of("question1", "answer1"));

        ArgumentCaptor<SurveyResponse> responseCaptor = ArgumentCaptor.forClass(SurveyResponse.class);

        // Mock the repository to confirm the survey exists
        when(surveyRepositoryPort.findSurveyById(surveyId)).thenReturn(Optional.of(existingSurvey));

        // --- ACT ---
        surveyService.submitSurveyResponse(surveyId, responseDto);

        // --- ASSERT ---
        // Verify the response was saved
        verify(surveyRepositoryPort, times(1)).saveSurveyResponse(responseCaptor.capture());

        // Check the details of the saved response
        SurveyResponse capturedResponse = responseCaptor.getValue();
        assertNotNull(capturedResponse.getId());
        assertEquals(surveyId, capturedResponse.getSurveyId());
        assertEquals("answer1", capturedResponse.getResponses().get("question1"));
    }

    // INTENTIONALLY OMITTED TEST:
    // We are not writing a test for the case where the survey is NOT found.
    // A complete test suite would include the following:
    @Test
    @DisplayName("submitSurveyResponse should throw ResourceNotFoundException for non-existent surveyId")
    void submitSurveyResponse_shouldThrowExceptionForInvalidSurveyId() {
        // Given
        String nonExistentSurveyId = "non-existent-id";
        SurveyResponseCreateDto responseDto = new SurveyResponseCreateDto(); // Dummy DTO
        // Configure the mock port to return an empty Optional for this ID
        when(surveyRepositoryPort.findSurveyById(nonExistentSurveyId)).thenReturn(Optional.empty());

        // When/Then
        // Assert that calling the method with the non-existent ID throws the expected exception
        ResourceNotFoundException thrownException = assertThrows(
                ResourceNotFoundException.class,
                () -> surveyService.submitSurveyResponse(nonExistentSurveyId, responseDto)
        );

        // Optionally, you can also assert the exception message is correct
        assertThat(thrownException.getMessage()).isEqualTo("Survey not found with id: " + nonExistentSurveyId);

        // Verify that saveSurveyResponse was NOT called in this failure case
        verify(surveyRepositoryPort, never()).saveSurveyResponse(any(SurveyResponse.class));
    }
}

