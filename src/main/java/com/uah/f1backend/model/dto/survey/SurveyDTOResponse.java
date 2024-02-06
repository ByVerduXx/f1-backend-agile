package com.uah.f1backend.model.dto.survey;

import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import com.uah.f1backend.model.dto.vote.VoteDTOResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class SurveyDTOResponse {
    private final Integer id;
    private final String permalink;
    private final String title;
    private final String description;
    private final Date limitDate;
    private final List<DriverDTOResponse> drivers;
    private final List<VoteDTOResponse> votes;
}
