package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.CarModel;
import com.uah.f1backend.model.dto.car.CarDTORequest;
import com.uah.f1backend.model.dto.car.CarDTOResponse;
import com.uah.f1backend.model.mapper.car.CarMappers;
import com.uah.f1backend.repository.CarModelRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.uah.f1backend.repository.TeamModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarModelRepository carModelRepository;
    private final TeamModelRepository teamModelRepository;

    public List<CarDTOResponse> getAllCars() {
        return CarMappers.toCarDTOResponses(carModelRepository.findAll());
    }

    public CarDTOResponse getCarById(Integer id) {
        return CarMappers.toCarDTOResponse(carModelRepository.findById(id).orElseThrow(HttpExceptions.CarDoesntExistException::new));
    }

    public CarDTOResponse saveCar(CarDTORequest carDTORequest) {
        try {
            CarModel carModel = CarMappers.toCarModel(carDTORequest);
            carModel.setTeam(teamModelRepository.findById(carDTORequest.getTeamId()).orElseThrow(HttpExceptions.TeamDoesntExistException::new));

            isCarNameInUse(carModel.getName());
            isCarCodeInUse(carModel.getCode());
            validateCarFields(carModel);
            return CarMappers.toCarDTOResponse(carModelRepository.save(carModel));
        } catch (Exception e) {
            throw new HttpExceptions.CarNotSavedException();
        }
    }

    public CarDTOResponse updateCar(Integer id, CarDTORequest carDTORequest) {
        try {
        CarModel carModel = carModelRepository.findById(id).orElseThrow(HttpExceptions.CarDoesntExistException::new);

        carModel.setName(carDTORequest.getName());
        carModel.setCode(carDTORequest.getCode());
        carModel.setErsGainSlow(carDTORequest.getErsGainSlow());
        carModel.setErsGainMedium(carDTORequest.getErsGainMedium());
        carModel.setErsGainFast(carDTORequest.getErsGainFast());
        carModel.setConsumption(carDTORequest.getConsumption());

        if (!Objects.equals(carDTORequest.getTeamId(), carModel.getTeam().getId())) {
            carModel.setTeam(teamModelRepository.findById(carDTORequest.getTeamId()).orElseThrow(HttpExceptions.TeamDoesntExistException::new));
        }

        isCarNameInUse(carModel.getName());
        isCarCodeInUse(carModel.getCode());
        validateCarFields(carModel);

        return CarMappers.toCarDTOResponse(carModelRepository.save(carModel));
        } catch (Exception e) {
            throw new HttpExceptions.CarNotSavedException();
        }
    }

    public String deleteCar(Integer id) {
        CarModel carModel = carModelRepository.findById(id).orElseThrow(HttpExceptions.CarDoesntExistException::new);
        carModelRepository.delete(carModel);
        return "Car with id " + id + " and name " + carModel.getName() + " has been deleted";
    }

    private void validateCarFields(CarModel car) {
        if (car.getName() == null || car.getName().isEmpty()) {
            throw new HttpExceptions.CarNameNotValidException();
        }
        if (car.getCode() == null || car.getCode().isEmpty() || car.getCode().length() < 3) {
            throw new HttpExceptions.CarCodeNotValidException();
        }
        validateErsValue(car.getErsGainSlow());
        validateErsValue(car.getErsGainMedium());
        validateErsValue(car.getErsGainFast());

        if (car.getConsumption() == null || car.getConsumption().compareTo(BigDecimal.ZERO) < 0) {
            throw new HttpExceptions.CarConsumptionNotValidException();
        }
    }

    private void validateErsValue(BigDecimal value) {
        if (value != null && value.compareTo(BigDecimal.ZERO) >= 0) {
            throw new HttpExceptions.CarErsValueNotValidException();
        }
    }

    private void isCarNameInUse(String name) {
        if (carModelRepository.findCarModelByName(name).isPresent()) {
            throw new HttpExceptions.CarNameInUseException();
        }
    }

    private void isCarCodeInUse(String code) {
        if (carModelRepository.findCarModelByCode(code).isPresent()) {
            throw new HttpExceptions.CarCodeInUseException();
        }
    }
}
