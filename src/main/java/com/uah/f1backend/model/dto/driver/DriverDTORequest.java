package com.uah.f1backend.model.dto.driver;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DriverDTORequest {

    private final String name;

    private final String lastName;

    private final String initial;

    private final String photo;

    private final String twitter;

    private final Integer idCountry;
}
