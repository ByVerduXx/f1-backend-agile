package com.uah.f1backend.model.dto.survey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class SurveyDTORequest {
    private final String permalink;
    private final String title;
    private final String description;
    private final Date limitDate;
    private final List<Integer> driverIds;
}
