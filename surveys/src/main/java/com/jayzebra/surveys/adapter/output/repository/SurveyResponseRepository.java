package com.jayzebra.surveys.adapter.output.repository;

import com.jayzebra.surveys.adapter.output.entity.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyResponseRepository extends JpaRepository<SurveyResponse, String> {
}