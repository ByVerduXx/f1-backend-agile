package com.uah.f1backend.service;

import static com.uah.f1backend.utils.CarUtils.dummyCarModel;
import static com.uah.f1backend.utils.CircuitUtils.dummyCircuitModel;
import static com.uah.f1backend.utils.SimulationUtils.*;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.repository.CarModelRepository;
import com.uah.f1backend.repository.CircuitModelRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SimulationServiceTest {

    @InjectMocks
    SimulationService simulationService;

    @Mock
    CircuitModelRepository circuitModelRepository;

    @Mock
    CarModelRepository carModelRepository;

    AutoCloseable closeable;

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void carIdNullTest() {
        Assertions.assertThrows(HttpExceptions.SimulationNotValidException.class, () -> {
            simulationService.simulate(null, 1);
        });
    }

    @Test
    void circuitIdNullTest() {
        Assertions.assertThrows(HttpExceptions.SimulationNotValidException.class, () -> {
            simulationService.simulate(1, null);
        });
    }

    @Test
    void carDoesntExistTest() {
        Mockito.doReturn(Optional.empty()).when(carModelRepository).findById(1);
        Mockito.doReturn(Optional.of(dummyCircuitModel()))
                .when(circuitModelRepository)
                .findById(1);

        Assertions.assertThrows(HttpExceptions.CarDoesntExistException.class, () -> {
            simulationService.simulate(1, 1);
        });
    }

    @Test
    void circuitDoesntExistTest() {
        Mockito.doReturn(Optional.of(dummyCarModel())).when(carModelRepository).findById(1);
        Mockito.doReturn(Optional.empty()).when(circuitModelRepository).findById(1);

        Assertions.assertThrows(HttpExceptions.CircuitDoesntExistException.class, () -> {
            simulationService.simulate(1, 1);
        });
    }

    @Test
    void simulationTest() {
        Mockito.doReturn(Optional.of(dummyCarModelForSimulation()))
                .when(carModelRepository)
                .findById(1);
        Mockito.doReturn(Optional.of(dummyCircuitModelForSimulation()))
                .when(circuitModelRepository)
                .findById(1);
        final var expectedSimulation = dummySimulationDTOResponse();
        final var actualSimulation = simulationService.simulate(1, 1);

        Assertions.assertEquals(expectedSimulation, actualSimulation);
    }
}
