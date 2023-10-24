package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.country.CountryDTOResponse;
import com.uah.f1backend.service.CountryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("countries")
@RequiredArgsConstructor
public class CountryRestController {

    private final CountryService countryService;

    @GetMapping()
    public ResponseEntity<List<CountryDTOResponse>> obtainAllCountries() {
        return ResponseEntity.ok(countryService.obtainAllCountries());
    }
}
