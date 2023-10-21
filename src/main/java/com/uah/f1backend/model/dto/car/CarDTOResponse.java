package com.uah.f1backend.model.dto.car;

import java.math.BigDecimal;
import lombok.*;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class CarDTOResponse {

    private final Integer id;
    private final String name;
    private final String code;
    private final BigDecimal ersGainSlow;
    private final BigDecimal ersGainMedium;
    private final BigDecimal ersGainFast;
    private final BigDecimal consumption;
    private final String teamName;
}
