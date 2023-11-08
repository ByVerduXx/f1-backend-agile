package com.uah.f1backend.model.mapper.driver;

import com.uah.f1backend.model.DriverModel;
import com.uah.f1backend.model.dto.driver.DriverDTORequest;
import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import com.uah.f1backend.model.mapper.country.CountryMappers;
import java.util.List;

public class DriverMappers {

    public static DriverDTOResponse toDriverDTOResponse(DriverModel dm) {
        final var idTeam = dm.getTeam() != null ? dm.getTeam().getId() : null;
        return new DriverDTOResponse(
                dm.getId(),
                dm.getName(),
                dm.getLastName(),
                dm.getInitial(),
                dm.getDorsal(),
                dm.getPhoto(),
                dm.getTwitter(),
                CountryMappers.toCountryDTOResponse(dm.getCountry()),
                idTeam);
    }

    public static List<DriverDTOResponse> toDriverDTOResponses(List<DriverModel> dml) {
        return dml.stream().map(DriverMappers::toDriverDTOResponse).toList();
    }

    public static DriverModel toDriverModel(DriverDTORequest ddreq) {
        try {
            final var dm = new DriverModel();
            dm.setName(ddreq.getName());
            dm.setLastName(ddreq.getLastName());
            dm.setInitial(ddreq.getInitial());
            dm.setDorsal(ddreq.getDorsal());
            dm.setPhoto(ddreq.getPhoto());
            dm.setTwitter(ddreq.getTwitter());

            return dm;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static List<DriverModel> toDriverModelReq(List<DriverDTORequest> ddreq) {
        return ddreq.stream().map(DriverMappers::toDriverModel).toList();
    }
}
