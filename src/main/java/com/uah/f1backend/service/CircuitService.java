package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.CircuitModel;
import com.uah.f1backend.model.dto.circuit.CircuitDTORequest;
import com.uah.f1backend.model.dto.circuit.CircuitDTOResponse;
import com.uah.f1backend.model.dto.circuit.DeletedCircuitDTOResponse;
import com.uah.f1backend.model.mapper.circuit.CircuitMappers;
import com.uah.f1backend.repository.CircuitModelRepository;
import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CircuitService {
    private final CircuitModelRepository circuitModelRepository;

    public List<CircuitDTOResponse> getAllCircuits() {
        return CircuitMappers.toCircuitListDTOResponse(circuitModelRepository.findAll());
    }

    // Retrieve the circuit matching the given id
    public CircuitDTOResponse getCircuitById(Integer id) {
        final var c = circuitModelRepository.findById(id).orElseThrow(HttpExceptions.CircuitDoesntExistException::new);
        return CircuitMappers.toCircuitDTOResponse(c);
    }

    public CircuitDTOResponse insertCircuit(CircuitDTORequest circuit) {
        final var cm = CircuitMappers.toCircuitModel(circuit);
        if (cm == null) {
            throw new HttpExceptions.CircuitNotSavedException();
        }
        if (circuitModelRepository.findByName(circuit.getName()).isPresent()) {
            throw new HttpExceptions.CircuitNameInUseException();
        }
        if (cm.getLaps() <= 0) {
            throw new HttpExceptions.CircuitLapsLessThanZeroException();
        }
        if (cm.getLength() <= 0) {
            throw new HttpExceptions.CircuitLenghtLessThanZeroException();
        }
        if (cm.getSlow_turns()+cm.getFast_turns()+cm.getMedium_turns() <= 0) {
            throw new HttpExceptions.CircuitTurnsLessThanZeroException();
        }
        return CircuitMappers.toCircuitDTOResponse(circuitModelRepository.save(cm));
    }

    public DeletedCircuitDTOResponse deleteCircuitById(Integer id) {
        final var c = circuitModelRepository.findById(id).orElseThrow(HttpExceptions.CircuitDoesntExistException::new);
        circuitModelRepository.deleteById(id);
        return new DeletedCircuitDTOResponse("Circuit deleted", c.getName());
    }

    public CircuitDTOResponse updateCircuitById(Integer id, CircuitDTORequest c) {


        CircuitModel cm =
                circuitModelRepository.findById(id).orElseThrow(HttpExceptions.CircuitDoesntExistException::new);

        //Validations of the model update
        if (c.getLaps() <= 0) {
            throw new HttpExceptions.CircuitLapsLessThanZeroException();
        }
        if (c.getLength() <= 0) {
            throw new HttpExceptions.CircuitLenghtLessThanZeroException();
        }
        if (c.getSlow_turns()+c.getFast_turns()+c.getMedium_turns() <= 0) {
            throw new HttpExceptions.CircuitTurnsLessThanZeroException();
        }

        cm.setName(c.getName());
        cm.setCity(c.getCity());
        cm.setId_country(c.getId_country());
        cm.setImage(c.getImage());
        cm.setLaps(c.getLaps());
        cm.setLength(c.getLength());
        cm.setSlow_turns(c.getSlow_turns());
        cm.setMedium_turns(c.getMedium_turns());
        cm.setFast_turns(c.getFast_turns());

        // Validate that name doesn't exist in other db team
        final var circuitWithSameName = circuitModelRepository.findByName(cm.getName());
        final var isUsedName = circuitWithSameName.isPresent();
        if (isUsedName && !Objects.equals(circuitWithSameName.get().getId(), cm.getId())) {
            throw new HttpExceptions.TeamNameInUseException();
        }


        circuitModelRepository.save(cm);
        return CircuitMappers.toCircuitDTOResponse(cm);
    }
}
