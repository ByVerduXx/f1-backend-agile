package com.uah.f1backend.service;

import com.uah.f1backend.model.CircuitModel;
import com.uah.f1backend.repository.TeamModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CircuitService {
    private final TeamModelRepository teamModelRepository;
    public List<CircuitModel> getAllCircuits(){

        return null;
    }
}
