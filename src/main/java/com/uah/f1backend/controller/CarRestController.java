package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.car.CarDTORequest;
import com.uah.f1backend.model.dto.car.CarDTOResponse;
import com.uah.f1backend.service.CarService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cars")
@RequiredArgsConstructor
public class CarRestController {
    private final CarService carService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<CarDTOResponse>> obtainAll() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @GetMapping("{id}")
    public ResponseEntity<CarDTOResponse> obtainById(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping("/team/{id}")
    public ResponseEntity<List<CarDTOResponse>> findAllTeamCars(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.findAllTeamCars(id));
    }

    @PostMapping
    public ResponseEntity<CarDTOResponse> saveCar(@RequestBody CarDTORequest carDTORequest) {
        return new ResponseEntity<>(carService.saveCar(carDTORequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTOResponse> updateCar(
            @PathVariable Integer id, @RequestBody CarDTORequest carDTORequest) {
        return new ResponseEntity<>(carService.updateCar(id, carDTORequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.deleteCar(id));
    }
}
