package com.uah.f1backend.model.dto.simulation;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class DrivingModeDTO {
    private final BigDecimal saving;
    private final BigDecimal normal;
    private final BigDecimal aggressive;
}
