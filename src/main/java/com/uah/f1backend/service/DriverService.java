package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.DriverModel;
import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.dto.driver.DriverDTORequest;
import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import com.uah.f1backend.model.mapper.driver.DriverMappers;
import com.uah.f1backend.repository.DriverModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.uah.f1backend.common.GenericValidations.isValidTwitter;
import static com.uah.f1backend.common.GenericValidations.isValidUrl;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverModelRepository driverModelRepository;

    public List<DriverDTOResponse> findAllDrivers(){
        return DriverMappers.toDriverDTOResponses(driverModelRepository.findAll());
    }

    public DriverDTOResponse findDriverById(Integer id){
        return DriverMappers.toDriverDTOResponse(driverModelRepository.findById(id)
                .orElseThrow(HttpExceptions.DriverDoesntExistException::new));
    }

    public DriverDTOResponse insertDriver(DriverDTORequest driverDTORequest){
        final var dm = DriverMappers.toDriverModel(driverDTORequest);

        if(dm == null) {
            throw new HttpExceptions.DriverNotSavedException();
        }

        validateDriverFields(dm);

        return DriverMappers.toDriverDTOResponse(driverModelRepository.save(dm));
    }

    public void deleteDriverByName(String name){
        final var dm = driverModelRepository.findByName(name)
                .orElseThrow(HttpExceptions.DriverDoesntExistException::new);

        driverModelRepository.delete(dm);
    }

    public void deleteDriverById(Integer id){
        final var dm = driverModelRepository.findById(id)
                .orElseThrow(HttpExceptions.DriverDoesntExistException::new);

        driverModelRepository.delete(dm);
    }

    public DriverDTOResponse updateDriverById(Integer id,DriverDTORequest driverDTORequest){
        final var dm = driverModelRepository.findById(id)
                .orElseThrow(HttpExceptions.DriverDoesntExistException::new);

        dm.setName(driverDTORequest.getName());
        dm.setLastName(driverDTORequest.getLastName());
        dm.setInitial(driverDTORequest.getInitial());
        dm.setTwitter(driverDTORequest.getTwitter());
        dm.setPhoto(driverDTORequest.getPhoto());
        dm.setIdCountry(driverDTORequest.getIdCountry());

        validateDriverFields(dm);

        return DriverMappers.toDriverDTOResponse(driverModelRepository.save(dm));
    }

    private static void validateDriverFields(DriverModel dm) {
        // Validate that photo is a valid url
        if (!isValidUrl(dm.getPhoto())) {
            throw new HttpExceptions.InvalidUrlFormatException();
        }

        // Validate that twitter is a valid Twitter account (can be empty)
        if (dm.getTwitter() != null && !isValidTwitter(dm.getTwitter())) {
            throw new HttpExceptions.InvalidTwitterFormatException();
        }
    }

}
