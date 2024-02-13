package com.uah.f1backend.model.dto.car;

import java.math.BigDecimal;
import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CarDTOResponse {

    private Integer id;
    private String name;
    private String code;
    private BigDecimal ersGainSlow;
    private BigDecimal ersGainMedium;
    private BigDecimal ersGainFast;
    private BigDecimal consumption;
    private String teamName;
}
