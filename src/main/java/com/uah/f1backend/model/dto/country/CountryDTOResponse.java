package com.uah.f1backend.model.dto.country;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode
@RequiredArgsConstructor
public class CountryDTOResponse {
    private final Integer id;
    private final String code;
    private final String name;
}
