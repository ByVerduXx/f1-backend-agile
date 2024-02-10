package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpExceptions;
import com.uah.f1backend.model.RaceModel;
import com.uah.f1backend.model.dto.race.RaceDTORequest;
import com.uah.f1backend.model.dto.race.RaceDTOResponse;
import com.uah.f1backend.model.mapper.race.RaceMappers;
import com.uah.f1backend.repository.CircuitModelRepository;
import com.uah.f1backend.repository.RaceModelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaceService {

    private final RaceModelRepository raceModelRepository;
    private final CircuitModelRepository circuitModelRepository;

    public List<RaceDTOResponse> getAllRaces() {
        return RaceMappers.toRaceListDTOResponse(raceModelRepository.findAll());
    }

    public RaceDTOResponse saveRace(RaceDTORequest raceDTORequest) {
        try {
            RaceModel raceModel = RaceMappers.toRaceModel(raceDTORequest);
            raceModel.setCircuit(circuitModelRepository
                    .findById(raceDTORequest.getCircuitId())
                    .orElseThrow(HttpExceptions.CircuitDoesntExistException::new));

            return RaceMappers.toRaceDTOResponse(raceModelRepository.save(raceModel));
        } catch (NullPointerException e) {
            throw new HttpExceptions.RaceNotSavedException();
        }
    }

    public RaceDTOResponse updateRace(Integer id, RaceDTORequest raceDTORequest) {
        try {
            RaceModel raceModel =
                    raceModelRepository.findById(id).orElseThrow(HttpExceptions.RaceDoesntExistException::new);

            raceModel.setName(raceDTORequest.getName());
            raceModel.setDate(raceDTORequest.getDate());
            raceModel.setSprint(raceDTORequest.getSprint());
            raceModel.setCircuit(circuitModelRepository
                    .findById(raceDTORequest.getCircuitId())
                    .orElseThrow(HttpExceptions.CircuitDoesntExistException::new));

            raceModelRepository.save(raceModel);

            return RaceMappers.toRaceDTOResponse(raceModel);

        } catch (NullPointerException e) {
            throw new HttpExceptions.RaceNotSavedException();
        }
    }

    public String deleteRace(Integer id) {
        RaceModel raceModel =
                raceModelRepository.findById(id).orElseThrow(HttpExceptions.RaceDoesntExistException::new);
        raceModelRepository.delete(raceModel);
        return "Race with id " + id + " and name " + raceModel.getName() + " has been deleted";
    }
}
