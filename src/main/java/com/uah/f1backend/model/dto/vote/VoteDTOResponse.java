package com.uah.f1backend.model.dto.vote;

import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class VoteDTOResponse {
    private final Integer id;
    private final String voterName;
    private final String voterEmail;
    private final DriverDTOResponse driver;
}
