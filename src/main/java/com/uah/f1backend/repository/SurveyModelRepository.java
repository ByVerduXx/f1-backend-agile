package com.uah.f1backend.repository;

import com.uah.f1backend.model.SurveyModel;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyModelRepository extends JpaRepository<SurveyModel, Integer> {
    List<SurveyModel> findAllByLimitDateBefore(Date date);

    List<SurveyModel> findAllByLimitDateAfter(Date date);
}
