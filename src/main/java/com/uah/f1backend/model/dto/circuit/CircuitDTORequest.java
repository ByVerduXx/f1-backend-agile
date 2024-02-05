package com.uah.f1backend.model.dto.circuit;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class CircuitDTORequest {
    private final String name;
    private final String city;
    private final Integer id_country;
    private final String image;
    private final Integer laps;
    private final Integer length;
    private final Integer slowTurns;
    private final Integer mediumTurns;
    private final Integer fastTurns;
}
