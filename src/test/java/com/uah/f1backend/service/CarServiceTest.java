package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.CarModel;
import com.uah.f1backend.model.dto.car.CarDTORequest;
import com.uah.f1backend.model.dto.car.CarDTOResponse;
import com.uah.f1backend.repository.CarModelRepository;
import com.uah.f1backend.repository.TeamModelRepository;
import com.uah.f1backend.utils.CarUtils;
import com.uah.f1backend.utils.TeamUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CarServiceTest {

    @InjectMocks
    CarService carService;

    @Mock
    CarModelRepository carModelRepository;

    @Mock
    TeamModelRepository teamModelRepository;

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
    void getAllCarsEmptyTest() {
        List<CarModel> carList = new ArrayList<CarModel>();

        Mockito.when(carModelRepository.findAll()).thenReturn(carList);
        List<CarDTOResponse> actualResultList = carService.getAllCars();

        Assertions.assertEquals(0, actualResultList.size());
    }

    @Test
    void getAllCarsTest() {
        List<CarModel> carList = CarUtils.dummyListCarModel();
        List<CarDTOResponse> expectedResultList = CarUtils.dummyListCarDTOResponse();

        Mockito.when(carModelRepository.findAll()).thenReturn(carList);
        List<CarDTOResponse> actualResultList = carService.getAllCars();

        for (int i = 0; i < actualResultList.size(); i++) {
            Assertions.assertEquals(expectedResultList.get(i), actualResultList.get(i));
        }
    }

    @Test
    void getCarByIdTest() {
        CarModel car = CarUtils.dummyCarModel();
        CarDTOResponse expectedResult = CarUtils.dummyCarDTOResponse();

        Mockito.when(carModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(car));
        CarDTOResponse actualResult = carService.getCarById(1);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void getCarByIdDoesntExistTest() {
        Mockito.when(carModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(HttpExceptions.CarDoesntExistException.class, () -> {
            carService.getCarById(1);
        });
    }

    @Test
    @Disabled
    void saveCarTest() {
        CarModel car = CarUtils.dummyCarModel();
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequest();
        CarDTOResponse expectedResult = CarUtils.dummyCarDTOResponse();

        Mockito.when(teamModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(car.getTeam()));
        Mockito.when(carModelRepository.save(Mockito.any(CarModel.class))).thenReturn(car);
        Mockito.when(carModelRepository.findCarModelByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(carModelRepository.findCarModelByCode(Mockito.anyString())).thenReturn(Optional.empty());

        CarDTOResponse actualResult = carService.saveCar(carDTORequest);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @Disabled
    void saveCarTeamDoesntExistTest() {
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequest();

        Mockito.when(teamModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(HttpExceptions.TeamDoesntExistException.class, () -> {
            carService.saveCar(carDTORequest);
        });
    }

    @Test
    @Disabled
    void saveNullCarTest() {
        Assertions.assertThrows(HttpExceptions.CarNotSavedException.class, () -> {
            carService.saveCar(null);
        });
    }

    @Test
    @Disabled
    void saveCarNameAlreadyInUseTest() {
        CarModel car = CarUtils.dummyCarModel();
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequest();

        Mockito.when(teamModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(car.getTeam()));
        Mockito.when(carModelRepository.findCarModelByName(Mockito.anyString())).thenReturn(Optional.of(car));

        Assertions.assertThrows(HttpExceptions.CarNameInUseException.class, () -> {
            carService.saveCar(carDTORequest);
        });
    }

    @Test
    @Disabled
    void saveCarCodeAlreadyInUseTest() {
        CarModel car = CarUtils.dummyCarModel();
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequest();

        Mockito.when(teamModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(car.getTeam()));
        Mockito.when(carModelRepository.findCarModelByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(carModelRepository.findCarModelByCode(Mockito.anyString())).thenReturn(Optional.of(car));

        Assertions.assertThrows(HttpExceptions.CarCodeInUseException.class, () -> {
            carService.saveCar(carDTORequest);
        });
    }

    @Test
    @Disabled
    void saveCarNameNotValidTest() {
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequestBadName();

        Mockito.when(teamModelRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(TeamUtils.dummyTeamModel()));

        Assertions.assertThrows(HttpExceptions.CarNameNotValidException.class, () -> {
            carService.saveCar(carDTORequest);
        });
    }

    @Test
    @Disabled
    void saveCarCodeNotValidTest() {
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequestBadCode();

        Mockito.when(teamModelRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(TeamUtils.dummyTeamModel()));

        Assertions.assertThrows(HttpExceptions.CarCodeNotValidException.class, () -> {
            carService.saveCar(carDTORequest);
        });
    }

    @Test
    @Disabled
    void saveCarErsNotValidTest() {
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequestBadErs();

        Mockito.when(teamModelRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(TeamUtils.dummyTeamModel()));

        Assertions.assertThrows(HttpExceptions.CarErsValueNotValidException.class, () -> {
            carService.saveCar(carDTORequest);
        });
    }

    @Test
    @Disabled
    void saveCarConsumptionNotValidTest() {
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequestBadConsumption();

        Mockito.when(teamModelRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(TeamUtils.dummyTeamModel()));

        Assertions.assertThrows(HttpExceptions.CarConsumptionNotValidException.class, () -> {
            carService.saveCar(carDTORequest);
        });
    }

    @Test
    @Disabled
    void updateCarTest() {
        CarModel car = CarUtils.dummyCarModel();
        CarModel car2 = CarUtils.dummyCarModel2();
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequest2();
        CarDTOResponse expectedResult = CarUtils.dummyCarDTOResponse2();

        Mockito.when(carModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(car));
        Mockito.when(teamModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(car.getTeam()));
        Mockito.when(carModelRepository.save(Mockito.any(CarModel.class))).thenReturn(car2);
        Mockito.when(carModelRepository.findCarModelByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(carModelRepository.findCarModelByCode(Mockito.anyString())).thenReturn(Optional.empty());

        CarDTOResponse actualResult = carService.updateCar(1, carDTORequest);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @Disabled
    void updateCarDoesntExistTest() {
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequest();

        Mockito.when(carModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(HttpExceptions.CarDoesntExistException.class, () -> {
            carService.updateCar(1, carDTORequest);
        });
    }

    @Test
    @Disabled
    void updateCarTeamDoesntExistTest() {
        CarModel car = CarUtils.dummyCarModel();
        CarDTORequest carDTORequest = CarUtils.dummyCarDTORequest2();

        Mockito.when(carModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(car));
        Mockito.when(teamModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(HttpExceptions.TeamDoesntExistException.class, () -> {
            carService.updateCar(1, carDTORequest);
        });
    }

    @Test
    @Disabled
    void updateNullCarTest() {
        Mockito.when(carModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(CarUtils.dummyCarModel()));

        Assertions.assertThrows(HttpExceptions.CarNotSavedException.class, () -> {
            carService.updateCar(1, null);
        });
    }

    @Test
    void deleteCarTest() {
        CarModel car = CarUtils.dummyCarModel();
        String expectedResult = "Car with id 1 and name name has been deleted";

        Mockito.when(carModelRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(car));
        carService.deleteCar(1);

        Mockito.verify(carModelRepository, Mockito.times(1)).delete(Mockito.any(CarModel.class));
        Assertions.assertEquals(expectedResult, carService.deleteCar(1));
    }
}
