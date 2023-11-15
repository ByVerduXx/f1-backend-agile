package com.uah.f1backend.model.dto.simulation;

import java.math.BigDecimal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class SimulationDTOResponse {

    private final Integer idCar;
    private final Integer idCircuit;
    private final BigDecimal fuelConsumptionPerLap;
    private final BigDecimal requiredFuel;
    private final DrivingModeDTO ersGainPerLap;
    private final DrivingModeDTO lapsToChargeErs;
}
