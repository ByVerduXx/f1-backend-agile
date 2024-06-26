package com.uah.f1backend.service;

import static com.uah.f1backend.utils.CircuitUtils.*;
import static com.uah.f1backend.utils.CountryUtils.dummyCountryModel;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.CircuitModel;
import com.uah.f1backend.model.dto.circuit.DeletedCircuitDTOResponse;
import com.uah.f1backend.repository.CircuitModelRepository;
import com.uah.f1backend.repository.CountryModelRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CircuitServiceTest {

    @Mock
    CircuitModelRepository circuitModelRepository;

    @Mock
    CountryModelRepository countryModelRepository;

    AutoCloseable closeable;
    CircuitService circuitService;

    @BeforeEach
    void initMocks() {
        closeable = MockitoAnnotations.openMocks(this);
        circuitService = new CircuitService(circuitModelRepository, countryModelRepository);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    // ---------------------------------
    @Test
    void getAllCircuitsEmptyTest() {
        final var circuitList = new ArrayList<CircuitModel>();
        Mockito.doReturn(circuitList).when(circuitModelRepository).findAll();
        final var actualResultList = circuitService.getAllCircuits();
        Assertions.assertEquals(0, actualResultList.size());
    }

    @Test
    void getAllCircuitsTest() {
        Mockito.doReturn(dummyListCircuitModel()).when(circuitModelRepository).findAll();
        final var actualResult = circuitService.getAllCircuits();
        Assertions.assertEquals(dummyListCircuitDTOResponse(), actualResult);
    }

    @Test
    void getCircuitByIdTest() {
        final var cm = dummyCircuitModel();

        Mockito.doReturn(Optional.of(cm)).when(circuitModelRepository).findById(1);

        final var actualResult = circuitService.getCircuitById(1);
        final var expectedResult = dummyCircuitDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getCircuitByIdNotFoundTest() {
        Mockito.doReturn(Optional.empty()).when(circuitModelRepository).findById(1);
        Assertions.assertThrows(HttpExceptions.CircuitDoesntExistException.class, () -> {
            circuitService.getCircuitById(1);
        });
    }

    @Test
    void insertCircuitTest() {
        final var circuitToInsert = dummyCircuitDTORequest();
        final var expectedResult = dummyCircuitDTOResponse();
        final var cm = dummyCircuitModel();

        Mockito.doReturn(dummyCircuitModel()).when(circuitModelRepository).save(dummyCircuitModel());

        Mockito.doReturn(Optional.of(dummyCountryModel()))
                .when(countryModelRepository)
                .findById(cm.getCountry().getId());

        final var actualResult = circuitService.insertCircuit(circuitToInsert);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void insertCircuitObjectNullTest() {
        Assertions.assertThrows(HttpExceptions.CircuitNotSavedException.class, () -> {
            circuitService.insertCircuit(null);
        });
    }

    @Test
    void deleteCircuitByIdTest() {
        final var circuitId = 1;
        final var cm = dummyCircuitModel();
        Mockito.doReturn(Optional.of(cm)).when(circuitModelRepository).findById(circuitId);
        Mockito.doNothing().when(circuitModelRepository).delete(cm);

        final var expectedResult = new DeletedCircuitDTOResponse("Circuit deleted", cm.getName());
        final var actualRestult = circuitService.deleteCircuitById(circuitId);

        Assertions.assertEquals(expectedResult, actualRestult);
    }

    @Test
    void updateCircuitByIdTest() {
        final var circuitId = 1;
        final var cm = dummyCircuitModel();

        Mockito.doReturn(Optional.of(cm)).when(circuitModelRepository).findById(circuitId);
        Mockito.doReturn(cm).when(circuitModelRepository).save(cm);

        final var circuitRequest = dummyCircuitDTORequest();
        final var expectedResult = dummyCircuitDTOResponse();
        final var actualResult = circuitService.updateCircuitById(circuitId, circuitRequest);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteCircuitByIdNotFoundTest() {
        final var circuitId = 1;
        Mockito.doReturn(Optional.empty()).when(circuitModelRepository).findById(circuitId);
        Assertions.assertThrows(HttpExceptions.CircuitDoesntExistException.class, () -> {
            circuitService.deleteCircuitById(circuitId);
        });
    }

    @Test
    void updateCircuitByIdNotFoundTest() {
        final var circuitId = 1;
        final var c = dummyCircuitDTORequest();
        Mockito.doReturn(Optional.empty()).when(circuitModelRepository).findById(circuitId);
        Assertions.assertThrows(HttpExceptions.CircuitDoesntExistException.class, () -> {
            circuitService.updateCircuitById(circuitId, c);
        });
    }
}
