package com.uah.f1backend.model.dto.circuit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CircuitDTORequest {
    private final String name;
    private final String city;
    private final String id_country;
    private final String image;
    private final String laps;
    private final String length;
    private final String slow_turns;
    private final String medium_turns;
    private final String fast_turns;
}
