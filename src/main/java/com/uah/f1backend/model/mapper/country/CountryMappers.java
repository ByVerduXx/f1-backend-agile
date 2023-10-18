package com.uah.f1backend.model.mapper.country;

import com.uah.f1backend.model.CountryModel;
import com.uah.f1backend.model.dto.country.CountryDTOResponse;

import java.util.List;

public class CountryMappers {
    public CountryDTOResponse toCountryDTOResponse(CountryModel cm){
        return new CountryDTOResponse(cm.getId(), cm.getCode(), cm.getName());
    }

    public CountryModel toCountryModel(CountryDTOResponse cdr){
        return new CountryModel(cdr.getId(), cdr.getCode(), cdr.getName());
    }

    public List<CountryDTOResponse> countryDTOResponses(List<CountryModel> cms){
        return cms.stream().map(this::toCountryDTOResponse).toList();
    }

    public List<CountryModel> countryModels(List<CountryDTOResponse> cdrs){
        return cdrs.stream().map(this::toCountryModel).toList();
    }
}
