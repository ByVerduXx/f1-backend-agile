package com.uah.f1backend.model.dto.car;

import java.math.BigDecimal;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class CarDTORequest {

    private String name;
    private String code;
    private BigDecimal ersGainSlow;
    private BigDecimal ersGainMedium;
    private BigDecimal ersGainFast;
    private BigDecimal consumption;
}
