package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.CarModel;
import com.uah.f1backend.model.dto.car.CarDTORequest;
import com.uah.f1backend.model.dto.car.CarDTOResponse;
import com.uah.f1backend.model.mapper.car.CarMappers;
import com.uah.f1backend.repository.CarModelRepository;
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
        CarModel carModel = CarMappers.toCarModel(carDTORequest);
        carModel.setTeam(teamModelRepository.findById(carDTORequest.getTeamId()).orElseThrow(HttpExceptions.TeamDoesntExistException::new));

        return CarMappers.toCarDTOResponse(carModelRepository.save(carModel));
    }

    public CarDTOResponse updateCar(Integer id, CarDTORequest carDTORequest) {
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

        return CarMappers.toCarDTOResponse(carModelRepository.save(carModel));
    }

    public String deleteCar(Integer id) {
        CarModel carModel = carModelRepository.findById(id).orElseThrow(HttpExceptions.CarDoesntExistException::new);
        carModelRepository.delete(carModel);
        return "Car with id " + id + " and name " + carModel.getName() + " has been deleted";
    }
}
