package com.uah.f1backend.model.dto.race;

import java.util.Date;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class RaceDTORequest {
    private final String name;
    private final Date date;
    private final Boolean sprint;
    private final Integer circuitId;
}
