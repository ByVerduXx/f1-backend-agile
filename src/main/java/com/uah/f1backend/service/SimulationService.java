package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.CarModel;
import com.uah.f1backend.model.CircuitModel;
import com.uah.f1backend.model.dto.simulation.DrivingModeDTO;
import com.uah.f1backend.model.dto.simulation.SimulationDTOResponse;
import com.uah.f1backend.repository.CarModelRepository;
import com.uah.f1backend.repository.CircuitModelRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimulationService {

    static final BigDecimal MAX_ERS_CHARGE = BigDecimal.valueOf(1.2);
    static final BigDecimal MAX_ERS_CHARGE_PER_LAP = BigDecimal.valueOf(0.6);

    private final CarModelRepository carModelRepository;
    private final CircuitModelRepository circuitModelRepository;

    public SimulationDTOResponse simulate(Integer idCar, Integer idCircuit) {

        if (idCar == null || idCircuit == null) {
            throw new HttpExceptions.SimulationNotValidException();
        }

        final var car = carModelRepository.findById(idCar).orElseThrow(HttpExceptions.CarDoesntExistException::new);
        final var circuit =
                circuitModelRepository.findById(idCircuit).orElseThrow(HttpExceptions.CircuitDoesntExistException::new);

        final var fuelConsumptionPerKm = car.getConsumption().divide(BigDecimal.valueOf(100));
        final var circuitLengthKm = BigDecimal.valueOf(circuit.getLength() / 1000.0);

        // Calculate fuel consumption per lap
        final var fuelConsumptionPerLap = fuelConsumptionPerKm.multiply(circuitLengthKm);

        // Calculate total fuel consumption
        final var requiredFuel = fuelConsumptionPerLap.multiply(BigDecimal.valueOf(circuit.getLaps()));

        // Driving style modifiers
        final var drivingStyleModifiers =
                List.of(BigDecimal.valueOf(1.05), BigDecimal.valueOf(0.75), BigDecimal.valueOf(0.4));

        // Calculate standard ERS gain per lap
        final BigDecimal ersGainPerLap = calculateStandardERSGainPerLap(car, circuit);

        // Calculate ERS gain per lap with driving style modifiers (maximum of battery capacity and recovery per lap)
        final var ersGainPerLapSaving =
                ersGainPerLap.multiply(drivingStyleModifiers.get(0)).min(MAX_ERS_CHARGE_PER_LAP);
        final var ersGainPerLapNormal =
                ersGainPerLap.multiply(drivingStyleModifiers.get(1)).min(MAX_ERS_CHARGE_PER_LAP);
        final var ersGainPerLapAggressive =
                ersGainPerLap.multiply(drivingStyleModifiers.get(2)).min(MAX_ERS_CHARGE_PER_LAP);

        // Calculate laps to charge ERS
        final var lapsToChargeErsSaving = MAX_ERS_CHARGE.divide(ersGainPerLapSaving, RoundingMode.UP);
        final var lapsToChargeErsNormal = MAX_ERS_CHARGE.divide(ersGainPerLapNormal, RoundingMode.UP);
        final var lapsToChargeErsAggressive = MAX_ERS_CHARGE.divide(ersGainPerLapAggressive, RoundingMode.UP);

        // Return simulation response
        return new SimulationDTOResponse(
                car.getId(),
                circuit.getId(),
                fuelConsumptionPerLap,
                requiredFuel,
                new DrivingModeDTO(ersGainPerLapSaving, ersGainPerLapNormal, ersGainPerLapAggressive),
                new DrivingModeDTO(lapsToChargeErsSaving, lapsToChargeErsNormal, lapsToChargeErsAggressive));
    }

    private static BigDecimal calculateStandardERSGainPerLap(CarModel car, CircuitModel circuit) {
        final var ersGainSlowTurns = car.getErsGainSlow().multiply(BigDecimal.valueOf(circuit.getSlow_turns()));
        final var ersGainMediumTurns = car.getErsGainMedium().multiply(BigDecimal.valueOf(circuit.getMedium_turns()));
        final var ersGainFastTurns = car.getErsGainFast().multiply(BigDecimal.valueOf(circuit.getFast_turns()));

        return ersGainSlowTurns.add(ersGainMediumTurns).add(ersGainFastTurns);
    }
}
