package com.uah.f1backend.utils;

import com.uah.f1backend.model.CountryModel;
import com.uah.f1backend.model.DriverModel;
import com.uah.f1backend.model.dto.country.CountryDTOResponse;

import java.util.ArrayList;
import java.util.List;

import static com.uah.f1backend.utils.TeamUtils.dummyTeamModel;

public class CountryUtils {
    public static CountryModel dummyCountryModel() {
        return new CountryModel(1, "CO", "country");
    }
    public static CountryDTOResponse dummyCountryDTOResponse() { return new CountryDTOResponse(1, "CO", "country");}

    public static List<CountryModel> dummyListCountryModel() {
        final var dummyList = new ArrayList<CountryModel>();
        for (var i = 0; i < 3; i++) {
            final var cm = new CountryModel();
            cm.setId(i);
            cm.setName("country" + i);
            cm.setCode("CO" + i);
            dummyList.add(cm);
        }
        return dummyList;
    }

    public static List<CountryDTOResponse> dummyListCountryDTOResponse() {
        final var dummyList = new ArrayList<CountryDTOResponse>();
        for (var i = 0; i < 3; i++) {
            final var cm = new CountryDTOResponse(
                    i,
                    "CO" + i,
                    "country" + i
            );
            dummyList.add(cm);
        }
        return dummyList;
    }
}
