package com.uah.f1backend.service;

import com.uah.f1backend.model.dto.country.CountryDTOResponse;
import com.uah.f1backend.model.mapper.country.CountryMappers;
import com.uah.f1backend.repository.CountryModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryModelRepository countryModelRepository;

    public List<CountryDTOResponse> obtainAllCountries(){
        return CountryMappers.toCountryDTOResponses(countryModelRepository.findAll());
    }
}
