package com.uah.f1backend.controller;

import com.uah.f1backend.service.CircuitService;
import com.uah.f1backend.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("circuits")
@RequiredArgsConstructor
public class CircuitRestcontroller {

    private final CircuitService circuitService;


}
