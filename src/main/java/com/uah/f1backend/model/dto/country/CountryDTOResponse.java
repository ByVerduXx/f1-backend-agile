package com.uah.f1backend.model.dto.country;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CountryDTOResponse {
    private final Integer id;
    private final String code;
    private final String name;
}