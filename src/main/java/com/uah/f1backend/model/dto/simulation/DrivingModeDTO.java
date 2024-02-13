package com.uah.f1backend.model.dto.simulation;

import java.math.BigDecimal;
import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DrivingModeDTO {
    private BigDecimal saving;
    private BigDecimal normal;
    private BigDecimal aggressive;
}
