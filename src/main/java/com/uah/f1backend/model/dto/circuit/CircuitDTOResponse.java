package com.uah.f1backend.model.dto.circuit;

import com.uah.f1backend.model.dto.country.CountryDTOResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class CircuitDTOResponse {
    private final Integer id;
    private final String name;
    private final String city;
    private final CountryDTOResponse country;
    private final String image;
    private final Integer laps;
    private final Integer length;
    private final Integer slow_turns;
    private final Integer medium_turns;
    private final Integer fast_turns;
}
