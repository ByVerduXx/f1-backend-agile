package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.car.CarDTOResponse;
import com.uah.f1backend.service.CarService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cars")
@RequiredArgsConstructor
public class CarRestController {
    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDTOResponse>> obtainAll() {
        return ResponseEntity.ok(carService.getAllCars());
    }
}
