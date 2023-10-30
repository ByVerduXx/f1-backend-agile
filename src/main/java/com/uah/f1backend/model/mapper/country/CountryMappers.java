package com.uah.f1backend.model.mapper.country;

import com.uah.f1backend.model.CountryModel;
import com.uah.f1backend.model.dto.country.CountryDTOResponse;
import java.util.List;

public class CountryMappers {
    public static CountryDTOResponse toCountryDTOResponse(CountryModel cm) {
        return new CountryDTOResponse(cm.getId(), cm.getCode(), cm.getName());
    }

    public static CountryModel toCountryModel(CountryDTOResponse cdr) {
        return new CountryModel(cdr.getId(), cdr.getCode(), cdr.getName());
    }

    public static List<CountryDTOResponse> toCountryDTOResponses(List<CountryModel> cms) {
        return cms.stream().map(CountryMappers::toCountryDTOResponse).toList();
    }

    public List<CountryModel> toCountryModels(List<CountryDTOResponse> cdrs) {
        return cdrs.stream().map(CountryMappers::toCountryModel).toList();
    }
}
