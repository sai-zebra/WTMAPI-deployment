package com.zebra.surveys.adapter.out;


import com.zebra.surveys.adapter.output.SurveyRepositoryAdapter;
import com.zebra.surveys.adapter.output.entity.Survey;
import com.zebra.surveys.adapter.output.entity.SurveyResponse;
import com.zebra.surveys.adapter.output.repository.SurveyRepository;
import com.zebra.surveys.adapter.output.repository.SurveyResponseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SurveyRepositoryAdapter.
 * This class ensures that the adapter correctly delegates all method calls
 * to its underlying repositories, thus satisfying mutation testing requirements.
 */
class SurveyRepositoryAdapterTest {

    @Mock
    private SurveyRepository surveyRepository; // Mock the survey repository

    @Mock
    private SurveyResponseRepository surveyResponseRepository; // Mock the survey response repository

    @InjectMocks
    private SurveyRepositoryAdapter surveyRepositoryAdapter; // Inject mocks into the adapter

    @BeforeEach
    void setUp() {
        // Initialize all mocks and inject them before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should delegate saveSurvey call to SurveyRepository")
    void saveSurvey_shouldDelegateToRepository() {
        // Given
        Survey surveyToSave = new Survey();
        surveyToSave.setId("survey-123");
        // We need to define what the mocked repository's save method should return
        when(surveyRepository.save(surveyToSave)).thenReturn(surveyToSave);

        // When
        Survey savedSurvey = surveyRepositoryAdapter.saveSurvey(surveyToSave);

        // Then
        // Verify the repository's save method was called once with the correct object
        verify(surveyRepository, times(1)).save(surveyToSave);
        // Verify the adapter returns the object that the repository returned
        assertThat(savedSurvey).isEqualTo(surveyToSave);
    }

    @Test
    @DisplayName("Should delegate saveSurveyResponse call to SurveyResponseRepository")
    void saveSurveyResponse_shouldDelegateToRepository() {
        // Given
        SurveyResponse surveyResponseToSave = new SurveyResponse();

        // When
        surveyRepositoryAdapter.saveSurveyResponse(surveyResponseToSave);

        // Then
        // Verify the repository's save method was called exactly once
        verify(surveyResponseRepository, times(1)).save(surveyResponseToSave);
    }

    @Test
    @DisplayName("Should delegate findSurveyById call to SurveyRepository")
    void findSurveyById_shouldDelegateToRepository() {
        // Given
        String surveyId = "survey-123";
        Survey expectedSurvey = new Survey();
        expectedSurvey.setId(surveyId);
        // Define what the mocked repository should return when findById is called
        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(expectedSurvey));

        // When
        Optional<Survey> foundSurvey = surveyRepositoryAdapter.findSurveyById(surveyId);

        // Then
        // Verify the repository's findById method was called once
        verify(surveyRepository, times(1)).findById(surveyId);
        // Verify the adapter returns the optional that the repository returned
        assertThat(foundSurvey).isPresent().contains(expectedSurvey);
    }

    @Test
    @DisplayName("Should delegate findAllSurveys call to SurveyRepository")
    void findAllSurveys_shouldDelegateToRepository() {
        // Given
        Survey survey = new Survey();
        List<Survey> expectedList = Collections.singletonList(survey);
        // Define what the mocked repository should return when findAll is called
        when(surveyRepository.findAll()).thenReturn(expectedList);

        // When
        List<Survey> foundSurveys = surveyRepositoryAdapter.findAllSurveys();

        // Then
        // Verify the repository's findAll method was called once
        verify(surveyRepository, times(1)).findAll();
        // Verify the adapter returns the list that the repository returned
        assertThat(foundSurveys).isEqualTo(expectedList);
    }
}
