package com.uah.f1backend.model.dto.car;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class CarDTORequest {

    private final String name;
    private final String code;
    private final BigDecimal ersGainSlow;
    private final BigDecimal ersGainMedium;
    private final BigDecimal ersGainFast;
    private final BigDecimal consumption;
    private final Integer teamId;
}
