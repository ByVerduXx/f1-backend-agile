package com.uah.f1backend.model.dto.simulation;

import java.math.BigDecimal;
import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SimulationDTOResponse {

    private Integer idCar;
    private Integer idCircuit;
    private BigDecimal fuelConsumptionPerLap;
    private BigDecimal requiredFuel;
    private DrivingModeDTO ersGainPerLap;
    private DrivingModeDTO lapsToChargeErs;
}
