package com.uah.f1backend.utils;

import static com.uah.f1backend.utils.CountryUtils.dummyCountryModel;
import static com.uah.f1backend.utils.TeamUtils.dummyTeamModel;

import com.uah.f1backend.model.CarModel;
import com.uah.f1backend.model.CircuitModel;
import com.uah.f1backend.model.dto.simulation.DrivingModeDTO;
import com.uah.f1backend.model.dto.simulation.SimulationDTOResponse;
import java.math.BigDecimal;

public class SimulationUtils {

    public static CircuitModel dummyCircuitModelForSimulation() {
        return new CircuitModel(1, "name", "city", dummyCountryModel(), "image", 57, 5412, 12, 7, 10);
    }

    public static CarModel dummyCarModelForSimulation() {
        return new CarModel(
                1,
                "name",
                "code",
                BigDecimal.valueOf(0.06),
                BigDecimal.valueOf(0.03),
                BigDecimal.valueOf(0.01),
                BigDecimal.valueOf(33.00),
                dummyTeamModel());
    }

    public static SimulationDTOResponse dummySimulationDTOResponse() {
        return new SimulationDTOResponse(
                1,
                1,
                BigDecimal.valueOf(1.78596),
                BigDecimal.valueOf(101.79972),
                new DrivingModeDTO(BigDecimal.valueOf(0.6), BigDecimal.valueOf(0.6), BigDecimal.valueOf(0.412)),
                new DrivingModeDTO(BigDecimal.valueOf(2.0), BigDecimal.valueOf(2.0), BigDecimal.valueOf(3.0)));
    }
}
