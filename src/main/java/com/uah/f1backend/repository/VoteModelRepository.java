package com.uah.f1backend.repository;

import com.uah.f1backend.model.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteModelRepository extends JpaRepository<VoteModel, Integer> {
    boolean existsByVoterNameOrVoterEmailAndSurveyIdAndDriverId(String voterName, String voterEmail, Integer surveyId, Integer driverId);
}
