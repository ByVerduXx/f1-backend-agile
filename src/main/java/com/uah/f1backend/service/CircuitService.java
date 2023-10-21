package com.uah.f1backend.service;

import com.uah.f1backend.model.dto.circuit.CircuitDTOResponse;
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








}
