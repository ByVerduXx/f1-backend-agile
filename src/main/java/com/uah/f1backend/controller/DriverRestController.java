package com.uah.f1backend.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.uah.f1backend.model.dto.driver.DeletedDriverDTOResponse;
import com.uah.f1backend.model.dto.driver.DriverDTORequest;
import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import com.uah.f1backend.service.DriverService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("drivers")
@RequiredArgsConstructor
public class DriverRestController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverDTOResponse>> obtainAllDrivers() {
        return ok(driverService.findAllDrivers());
    }

    @GetMapping("{id}")
    public ResponseEntity<DriverDTOResponse> obtainDriverById(@PathVariable Integer id) {
        return ok(driverService.findDriverById(id));
    }

    @PostMapping()
    public ResponseEntity<DriverDTOResponse> insertDriver(@RequestBody DriverDTORequest driverDTORequest) {
        return new ResponseEntity<>(driverService.insertDriver(driverDTORequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeletedDriverDTOResponse> deleteDriverById(@PathVariable Integer id) {
        return ok(driverService.deleteDriverById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<DriverDTOResponse> updateDriverById(
            @PathVariable Integer id, @RequestBody DriverDTORequest driverDTORequest) {
        return new ResponseEntity<>(driverService.updateDriverById(id, driverDTORequest), HttpStatus.CREATED);
    }
}
