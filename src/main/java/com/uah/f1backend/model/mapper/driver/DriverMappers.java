package com.uah.f1backend.model.mapper.driver;

import com.uah.f1backend.model.DriverModel;
import com.uah.f1backend.model.dto.driver.DriverDTORequest;
import com.uah.f1backend.model.dto.driver.DriverDTOResponse;

import java.util.List;

public class DriverMappers {

    public DriverDTORequest toDriverDTORequest(DriverModel dm){
        return new DriverDTORequest(dm.getName(), dm.getLastName(), dm.getInitial(), dm.getPhoto(), dm.getTwitter(), dm.getIdCountry());
    }

    public DriverDTOResponse toDriverDTOResponse(DriverModel dm){
        return new DriverDTOResponse(dm.getId(), dm.getName(), dm.getLastName(), dm.getInitial(), dm.getPhoto(), dm.getTwitter(), dm.getIdCountry());
    }

    public List<DriverDTORequest> driverDTORequests(List<DriverModel> dml){
        return dml.stream().map(this::toDriverDTORequest).toList();
    }

    public List<DriverDTOResponse> driverDTOResponses(List<DriverModel> dml){
        return dml.stream().map(this::toDriverDTOResponse).toList();
    }

    public DriverModel toDriverModel(DriverDTORequest ddreq){
        final var dm = new DriverModel();
        dm.setName(ddreq.getName());
        dm.setLastName(ddreq.getLastName());
        dm.setInitial(ddreq.getInitial());
        dm.setPhoto(ddreq.getPhoto());
        dm.setTwitter(ddreq.getTwitter());
        dm.setIdCountry(ddreq.getIdCountry());
        return dm;
    }

    public DriverModel toDriverModel(DriverDTOResponse ddres){
        final var dm = new DriverModel();
        dm.setId(ddres.getId());
        dm.setName(ddres.getName());
        dm.setLastName(ddres.getLastName());
        dm.setInitial(ddres.getInitial());
        dm.setPhoto(ddres.getPhoto());
        dm.setTwitter(ddres.getTwitter());
        dm.setIdCountry(ddres.getIdCountry());
        return dm;
    }

    public List<DriverModel> toDriverModelReq(List<DriverDTORequest> ddreq){
        return ddreq.stream().map(this::toDriverModel).toList();
    }

    public List<DriverModel> toDriverModelRes(List<DriverDTOResponse> ddres){
        return ddres.stream().map(this::toDriverModel).toList();
    }


}
