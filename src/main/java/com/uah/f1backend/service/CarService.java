package com.uah.f1backend.service;

import com.uah.f1backend.model.dto.car.CarDTOResponse;
import com.uah.f1backend.model.mapper.car.CarMappers;
import com.uah.f1backend.repository.CarModelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarModelRepository carModelRepository;

    public List<CarDTOResponse> getAllCars() {
        return CarMappers.toCarDTOResponses(carModelRepository.findAll());
    }
}
