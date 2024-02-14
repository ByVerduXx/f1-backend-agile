package com.uah.f1backend.model.dto.vote;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class VoteDTORequest {
    private final Integer surveyId;
    private final Integer driverId;
    private final String voterName;
    private final String voterEmail;
}
