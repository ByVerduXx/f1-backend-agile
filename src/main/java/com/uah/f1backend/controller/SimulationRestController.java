package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.simulation.SimulationDTOResponse;
import com.uah.f1backend.service.SimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("simulations")
@RequiredArgsConstructor
public class SimulationRestController {

    private final SimulationService simulationService;

    @GetMapping(params = {"idCar", "idCircuit"})
    public SimulationDTOResponse obtainSimulation(@RequestParam Integer idCar, @RequestParam Integer idCircuit) {
        return simulationService.simulate(idCar, idCircuit);
    }
}
