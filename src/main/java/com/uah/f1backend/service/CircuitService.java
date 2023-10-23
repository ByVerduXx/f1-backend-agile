package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.CircuitModel;
import com.uah.f1backend.model.dto.circuit.CircuitDTORequest;
import com.uah.f1backend.model.dto.circuit.CircuitDTOResponse;
import com.uah.f1backend.model.dto.circuit.DeletedCircuitDTOResponse;
import com.uah.f1backend.model.mapper.circuit.CircuitMappers;
import com.uah.f1backend.repository.CircuitModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CircuitService {
    private final CircuitModelRepository circuitModelRepository;
    public List<CircuitDTOResponse> getAllCircuits(){
        return CircuitMappers.toCircuitListDTOResponse(circuitModelRepository.findAll());
    }

    public CircuitDTOResponse getCircuitByName(String name){
        final var c = circuitModelRepository.findByName(name)
                .orElseThrow(HttpExceptions.CircuitDoesntExistException::new);
        return CircuitMappers.toCircuitDTOResponse(c);
    }

    // Retrieve the circuit matching the given id
    public CircuitDTOResponse getCircuitById(Integer id){
        final var c = circuitModelRepository.findById(Long.valueOf(id))
                .orElseThrow(HttpExceptions.CircuitDoesntExistException::new);
        return CircuitMappers.toCircuitDTOResponse(c);
    }


    public DeletedCircuitDTOResponse deleteCircuitByName(String name){
        final var c = circuitModelRepository.findByName(name)
                .orElseThrow(HttpExceptions.CircuitDoesntExistException::new);
        circuitModelRepository.delete(c);
        return new DeletedCircuitDTOResponse("Circuit deleted", name);
    }


    public CircuitDTOResponse insertCircuit(CircuitDTORequest circuit) {
        final var cm = CircuitMappers.toCircuitModel(circuit);
        if (cm == null) {
            throw new HttpExceptions.CircuitNotSavedException();
        }
        return CircuitMappers.toCircuitDTOResponse(circuitModelRepository.save(cm));
    }

    public DeletedCircuitDTOResponse deleteCircuitById(Integer id){
        final var c = circuitModelRepository.findById(Long.valueOf(id))
                .orElseThrow(HttpExceptions.CircuitDoesntExistException::new);
        circuitModelRepository.deleteById(Long.valueOf(id));
        return new DeletedCircuitDTOResponse("Circuit deleted", c.getName());
    }

    public CircuitDTOResponse updateCircuitById(int circuitId, CircuitDTORequest c) {
        CircuitModel cm = circuitModelRepository.findById(Long.valueOf(circuitId))
                .orElseThrow(HttpExceptions.CircuitDoesntExistException::new);
        cm.setName(c.getName());
        cm.setCity(c.getCity());
        cm.setId_country(c.getId_country());
        cm.setImage(c.getImage());
        cm.setLaps(c.getLaps());
        cm.setLength(c.getLength());
        cm.setSlow_turns(c.getSlow_turns());
        cm.setMedium_turns(c.getMedium_turns());
        cm.setFast_turns(c.getFast_turns());
        circuitModelRepository.save(cm);
        return CircuitMappers.toCircuitDTOResponse(cm);
    }
}
