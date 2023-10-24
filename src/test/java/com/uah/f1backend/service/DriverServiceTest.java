package com.uah.f1backend.service;


import com.uah.f1backend.model.DriverModel;
import com.uah.f1backend.repository.DriverModelRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static com.uah.f1backend.utils.DriverUtils.*;
import static com.uah.f1backend.configuration.HttpExceptions.*;

public class DriverServiceTest {

    @Mock
    private DriverModelRepository driverModelRepository;

    private AutoCloseable closeable;
    private DriverService driverService;

    @BeforeEach
    public void initMocks(){
        this.closeable = MockitoAnnotations.openMocks(this);
        this.driverService = new DriverService(driverModelRepository);
    }

    @AfterEach
    public void closeService() throws Exception{
        this.closeable.close();
    }

    @Test
    public void findAllDriversEmptyTest() {
        final var driverList = new ArrayList<DriverModel>();
        Mockito.doReturn(driverList).when(driverModelRepository).findAll();
        final var actualResultList = driverService.findAllDrivers();
        Assertions.assertEquals(0, actualResultList.size());
    }

    @Test
    public void findAllDriversTest() {
        final var driverList = dummyListDriverModel();
        Mockito.doReturn(driverList).when(driverModelRepository).findAll();
        final var actualResult = driverService.findAllDrivers();
        final var expectedResult = dummyListDriverDTOResponse();

        for (var i = 0; i < actualResult.size(); i++){
            Assertions.assertEquals(expectedResult.get(i), actualResult.get(i));
        }
    }

    @Test
    public void findDriverByIdTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findById(dm.getId());
        final var actualResult = driverService.findDriverById(1);
        final var expectedResult = dummyDriverDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findDriverByIdNotFoundTest() {
        Mockito.doReturn(Optional.empty()).when(driverModelRepository).findById(1);
        Assertions.assertThrows(DriverDoesntExistException.class, () -> driverService.findDriverById(1));
    }

    @Test
    public void findDriverByDorsalTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findByDorsal(dm.getDorsal());
        final var actualResult = driverService.findDriverByDorsal(1);
        final var expectedResult = dummyDriverDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findDriverByDorsalNotFoundTest() {
        Mockito.doReturn(Optional.empty()).when(driverModelRepository).findByDorsal(1);
        Assertions.assertThrows(DriverDoesntExistException.class, () -> driverService.findDriverByDorsal(1));
    }

    @Test
    public void insertDriverTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(dm).when(driverModelRepository).save(dm);
        final var actualResult = driverService.insertDriver(dummyDriverDTORequest());
        final var expectedResult = dummyDriverDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void insertDriverNullTest() {
        Assertions.assertThrows(DriverNotSavedException.class, () -> driverService.insertDriver(null));
    }

    @Test
    public void updateDriverByIdTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findById(dm.getId());
        Mockito.doReturn(dm).when(driverModelRepository).save(dm);
        final var actualResult = driverService.updateDriverById(dm.getId(), dummyDriverDTORequest());
        final var expectedResult = dummyDriverDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateDriverByIdNotFoundTest() {
        Mockito.doReturn(Optional.empty()).when(driverModelRepository).findById(1);
        Assertions.assertThrows(DriverDoesntExistException.class, () -> driverService.updateDriverById(1, dummyDriverDTORequest()));
    }

    @Test
    public void updateDriverByDorsalTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findByDorsal(dm.getDorsal());
        Mockito.doReturn(dm).when(driverModelRepository).save(dm);
        final var actualResult = driverService.updateDriverByDorsal(dm.getDorsal(), dummyDriverDTORequest());
        final var expectedResult = dummyDriverDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateDriverByDorsalNotFoundTest() {
        Mockito.doReturn(Optional.empty()).when(driverModelRepository).findByDorsal(1);
        Assertions.assertThrows(DriverDoesntExistException.class, () -> driverService.updateDriverByDorsal(1, dummyDriverDTORequest()));
    }

    @Test
    public void deleteDriverByIdTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findById(dm.getId());
        final var actualResult = driverService.deleteDriverById(dm.getId());
        final var expectedResult = dummyDeletedDriverDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteDriverByIdNotFoundTest() {
        Mockito.doReturn(Optional.empty()).when(driverModelRepository).findById(1);
        Assertions.assertThrows(DriverDoesntExistException.class, () -> driverService.deleteDriverById(1));
    }

}
