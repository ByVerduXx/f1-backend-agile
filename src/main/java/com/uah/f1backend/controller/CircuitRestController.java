package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.circuit.CircuitDTORequest;
import com.uah.f1backend.model.dto.circuit.CircuitDTOResponse;
import com.uah.f1backend.model.dto.circuit.DeletedCircuitDTOResponse;
import com.uah.f1backend.service.CircuitService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("circuits")
@RequiredArgsConstructor
public class CircuitRestController {

    private final CircuitService circuitService;

    @GetMapping
    public ResponseEntity<List<CircuitDTOResponse>> getCircuits() {
        return ResponseEntity.ok(circuitService.getAllCircuits());
    }

    @GetMapping("{id}")
    public ResponseEntity<CircuitDTOResponse> getCircuit(@PathVariable Integer id) {
        return ResponseEntity.ok(circuitService.getCircuitById(id));
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CircuitDTOResponse> insertCircuit(@RequestBody CircuitDTORequest circuit) {
        return new ResponseEntity<>(circuitService.insertCircuit(circuit), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DeletedCircuitDTOResponse> deleteCircuitById(@PathVariable Integer id) {
        return ResponseEntity.ok(circuitService.deleteCircuitById(id));
    }

    @PutMapping("{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CircuitDTOResponse> updateCircuitById(
            @PathVariable Integer id, @RequestBody CircuitDTORequest circuit) {
        return new ResponseEntity<>(circuitService.updateCircuitById(id, circuit), HttpStatus.CREATED);
    }
}
