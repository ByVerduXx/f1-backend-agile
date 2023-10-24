package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.driver.DeletedDriverDTOResponse;
import com.uah.f1backend.model.dto.driver.DriverDTORequest;
import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import com.uah.f1backend.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("drivers")
@RequiredArgsConstructor
public class DriverRestController {

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverDTOResponse>> obtainAllDrivers(){
        return ok(driverService.findAllDrivers());
    }

    @GetMapping("{id}")
    public ResponseEntity<DriverDTOResponse> obtainDriverById(@PathVariable Integer id){
        return ok(driverService.findDriverById(id));
    }

    @GetMapping(params = "dorsal")
    public ResponseEntity<DriverDTOResponse> obtainDriverByDorsal(@RequestParam Integer dorsal){
        return ok(driverService.findDriverByDorsal(dorsal));
    }

    @PostMapping()
    public ResponseEntity<DriverDTOResponse> insertDriver(@RequestBody DriverDTORequest driverDTORequest){
        return new ResponseEntity<>(driverService.insertDriver(driverDTORequest), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeletedDriverDTOResponse> deleteDriverById(@PathVariable Integer id){
        return ok(driverService.deleteDriverById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<DriverDTOResponse> updateDriverById(@PathVariable Integer id, @RequestBody DriverDTORequest driverDTORequest){
        return new ResponseEntity<>(driverService.updateDriverById(id, driverDTORequest), HttpStatus.CREATED);
    }

    @PutMapping(params = "dorsal")
    public ResponseEntity<DriverDTOResponse> updateDriverByDorsal(@RequestParam Integer dorsal, @RequestBody DriverDTORequest driverDTORequest){
        return new ResponseEntity<>(driverService.updateDriverByDorsal(dorsal, driverDTORequest), HttpStatus.CREATED);
    }
}
