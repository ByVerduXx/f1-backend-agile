package com.uah.f1backend.service;

import static com.uah.f1backend.configuration.HttpExceptions.*;
import static com.uah.f1backend.utils.CountryUtils.*;
import static com.uah.f1backend.utils.DriverUtils.*;
import static com.uah.f1backend.utils.TeamUtils.*;

import com.uah.f1backend.model.DriverModel;
import com.uah.f1backend.repository.CountryModelRepository;
import com.uah.f1backend.repository.DriverModelRepository;
import com.uah.f1backend.repository.TeamModelRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class DriverServiceTest {

    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverModelRepository driverModelRepository;

    @Mock
    private CountryModelRepository countryModelRepository;

    @Mock
    private TeamModelRepository teamModelRepository;

    private AutoCloseable closeable;

    @BeforeEach
    public void initMocks() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.driverService = new DriverService(driverModelRepository, teamModelRepository, countryModelRepository);
    }

    @AfterEach
    public void closeService() throws Exception {
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

        for (var i = 0; i < actualResult.size(); i++) {
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
    public void insertDriverTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dummyTeamModel()))
                .when(teamModelRepository)
                .findById(dm.getTeam().getId());
        Mockito.doReturn(Optional.of(dummyCountryModel()))
                .when(countryModelRepository)
                .findById(dm.getCountry().getId());
        Mockito.doReturn(dm).when(driverModelRepository).save(dm);
        final var actualResult = driverService.insertDriver(dummyDriverDTORequest());
        final var expectedResult = dummyDriverDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void insertDriverNullTeamTest() {
        final var dm = dummyDriverNullTeamModel();
        Mockito.doReturn(Optional.of(dummyCountryModel()))
                .when(countryModelRepository)
                .findById(dm.getCountry().getId());
        Mockito.doReturn(dm).when(driverModelRepository).save(dm);
        final var actualResult = driverService.insertDriver(dummyDriverDTONullTeamRequest());
        final var expectedResult = dummyDriverDTONullTeamResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void insertDriverNullTest() {
        Assertions.assertThrows(DriverNotSavedException.class, () -> driverService.insertDriver(null));
    }

    @Test
    public void insertDriverDorsalInUseTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findByDorsal(dm.getDorsal());
        Assertions.assertThrows(
                DriverDorsalInUseException.class, () -> driverService.insertDriver(dummyDriverDTORequest()));
    }

    @Test
    public void insertDriverCountryNotFoundTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dummyTeamModel()))
                .when(teamModelRepository)
                .findById(dm.getTeam().getId());
        Mockito.doReturn(Optional.empty())
                .when(countryModelRepository)
                .findById(dm.getCountry().getId());
        Assertions.assertThrows(
                CountryDoesntExistException.class, () -> driverService.insertDriver(dummyDriverDTORequest()));
    }

   @Test
   public void insertDriverTeamNotFoundTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.empty())
                .when(teamModelRepository)
                .findById(dm.getTeam().getId());
        Mockito.doReturn(Optional.of(dummyCountryModel()))
                .when(countryModelRepository)
                .findById(dm.getCountry().getId());
        Assertions.assertThrows(
                TeamDoesntExistException.class, () -> driverService.insertDriver(dummyDriverDTORequest()));
   }

    @Test
    public void updateDriverByIdTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findById(dm.getId());
        Mockito.doReturn(Optional.of(dummyTeamModel()))
                .when(teamModelRepository)
                .findById(dm.getTeam().getId());
        Mockito.doReturn(Optional.of(dummyCountryModel()))
                .when(countryModelRepository)
                .findById(dm.getCountry().getId());
        Mockito.doReturn(dm).when(driverModelRepository).save(dm);
        final var actualResult = driverService.updateDriverById(dm.getId(), dummyDriverDTORequest());
        final var expectedResult = dummyDriverDTOResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateDriverByIdNullTeamTest() {
        final var dm = dummyDriverNullTeamModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findById(dm.getId());
        Mockito.doReturn(Optional.of(dummyCountryModel()))
                .when(countryModelRepository)
                .findById(dm.getCountry().getId());
        Mockito.doReturn(dm).when(driverModelRepository).save(dm);
        final var actualResult = driverService.updateDriverById(dm.getId(), dummyDriverDTONullTeamRequest());
        final var expectedResult = dummyDriverDTONullTeamResponse();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateDriverByIdNotFoundTest() {
        Mockito.doReturn(Optional.empty()).when(driverModelRepository).findById(1);
        Assertions.assertThrows(
                DriverDoesntExistException.class, () -> driverService.updateDriverById(1, dummyDriverDTORequest()));
    }

    @Test
    public void updateDriverDorsalInUseTest() {
        final var dm = dummyDriverModel();
        final var dm2 = dummy2DriverModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findById(dm.getId());
        Mockito.doReturn(Optional.of(dm2)).when(driverModelRepository).findByDorsal(dm2.getDorsal());
        Assertions.assertThrows(
                DriverDorsalInUseException.class,
                () -> driverService.updateDriverById(dm.getId(), dummy3DriverDTORequest()));
    }

    @Test
    public void updateDriverCountryNotFoundTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findById(dm.getId());
        Mockito.doReturn(Optional.of(dummyTeamModel()))
                .when(teamModelRepository)
                .findById(dm.getTeam().getId());
        Mockito.doReturn(Optional.empty())
                .when(countryModelRepository)
                .findById(dm.getCountry().getId());
        Assertions.assertThrows(
                CountryDoesntExistException.class,
                () -> driverService.updateDriverById(dm.getId(), dummyDriverDTORequest()));
    }

    @Test
    public void updateDriverTeamNotFoundTest() {
        final var dm = dummyDriverModel();
        Mockito.doReturn(Optional.of(dm)).when(driverModelRepository).findById(dm.getId());
        Mockito.doReturn(Optional.empty())
                .when(teamModelRepository)
                .findById(dm.getTeam().getId());
        Mockito.doReturn(Optional.of(dummyCountryModel()))
                .when(countryModelRepository)
                .findById(dm.getCountry().getId());
        Assertions.assertThrows(
                TeamDoesntExistException.class,
                () -> driverService.updateDriverById(dm.getId(), dummyDriverDTORequest()));
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
