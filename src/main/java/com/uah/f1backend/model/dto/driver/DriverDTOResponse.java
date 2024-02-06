package com.uah.f1backend.model.dto.driver;

import com.uah.f1backend.model.dto.country.CountryDTOResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public class DriverDTOResponse {

    private final Integer id;

    private final String name;

    private final String lastName;

    private final String initial;

    private final Integer dorsal;

    private final String photo;

    private final String twitter;

    private final CountryDTOResponse country;

    private final Integer idTeam;
}
