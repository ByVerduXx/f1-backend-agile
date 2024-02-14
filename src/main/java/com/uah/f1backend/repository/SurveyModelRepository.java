package com.uah.f1backend.repository;

import com.uah.f1backend.model.SurveyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SurveyModelRepository extends JpaRepository<SurveyModel, Integer> {
    List<SurveyModel> findAllByLimitDateBefore(Date date);
    List<SurveyModel> findAllByLimitDateAfter(Date date);
}
