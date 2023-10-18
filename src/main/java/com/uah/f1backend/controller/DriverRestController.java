package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.driver.DriverDTORequest;
import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import com.uah.f1backend.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("drivers")
@RequiredArgsConstructor
public class DriverRestController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverDTOResponse>> obtainAllDrivers(){
        return ResponseEntity.ok(driverService.findAllDrivers());
    }

    @GetMapping("{id}")
    public ResponseEntity<DriverDTOResponse> obtainDriverById(@PathVariable Integer id){
        return ResponseEntity.ok(driverService.findDriverById(id));
    }

    @PostMapping()
    public ResponseEntity<DriverDTOResponse> insertDriver(@RequestBody DriverDTORequest driverDTORequest){
        return new ResponseEntity<>(driverService.insertDriver(driverDTORequest), HttpStatus.CREATED);
    }

    @DeleteMapping(params = "name")
    public ResponseEntity<Void> deleteDriverByName(@RequestParam String name){
        driverService.deleteDriverByName(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDriverById(@PathVariable Integer id){
        driverService.deleteDriverById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{id}")
    public ResponseEntity<DriverDTOResponse> updateDriverById(@PathVariable Integer id, @RequestBody DriverDTORequest driverDTORequest){
        return new ResponseEntity<>(driverService.updateDriverById(id, driverDTORequest), HttpStatus.CREATED);
    }
}
