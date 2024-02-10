package com.uah.f1backend.model.mapper.race;

import com.uah.f1backend.model.RaceModel;
import com.uah.f1backend.model.dto.race.RaceDTORequest;
import com.uah.f1backend.model.dto.race.RaceDTOResponse;
import com.uah.f1backend.model.mapper.circuit.CircuitMappers;
import java.util.List;

public class RaceMappers {

    public static RaceModel toRaceModel(RaceDTORequest dto) {
        return RaceModel.builder()
                .name(dto.getName())
                .date(dto.getDate())
                .sprint(dto.getSprint())
                .build();
    }

    public static RaceDTOResponse toRaceDTOResponse(RaceModel model) {
        return new RaceDTOResponse(
                model.getId(),
                model.getName(),
                model.getDate(),
                model.getSprint(),
                CircuitMappers.toCircuitDTOResponse(model.getCircuit()));
    }

    public static List<RaceDTOResponse> toRaceListDTOResponse(List<RaceModel> models) {
        return models.stream().map(RaceMappers::toRaceDTOResponse).toList();
    }
}
