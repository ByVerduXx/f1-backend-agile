package com.uah.f1backend.model.dto.race;

import com.uah.f1backend.model.dto.circuit.CircuitDTOResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class RaceDTOResponse {
    private final Integer id;
    private final String name;
    private final Date date;
    private final Boolean sprint;
    private final CircuitDTOResponse circuit;
}
