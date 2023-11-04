package com.uah.f1backend.model.mapper.circuit;

import com.uah.f1backend.model.CircuitModel;
import com.uah.f1backend.model.dto.circuit.CircuitDTORequest;
import com.uah.f1backend.model.dto.circuit.CircuitDTOResponse;
import java.util.List;

public class CircuitMappers {

    public static CircuitDTORequest toCircuitDTORequest(CircuitModel cm) {
        try {
            return new CircuitDTORequest(
                    cm.getName(),
                    cm.getCity(),
                    cm.getCountry().getId(),
                    cm.getImage(),
                    cm.getLaps(),
                    cm.getLength(),
                    cm.getSlow_turns(),
                    cm.getMedium_turns(),
                    cm.getFast_turns());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static CircuitDTOResponse toCircuitDTOResponse(CircuitModel cm) {
        try {
            return new CircuitDTOResponse(
                    cm.getId(),
                    cm.getName(),
                    cm.getCity(),
                    cm.getCountry().getId(),
                    cm.getImage(),
                    cm.getLaps(),
                    cm.getLength(),
                    cm.getSlow_turns(),
                    cm.getMedium_turns(),
                    cm.getFast_turns());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static List<CircuitDTOResponse> toCircuitListDTOResponse(List<CircuitModel> cm) {
        return cm.stream().map(CircuitMappers::toCircuitDTOResponse).toList();
    }

    public static CircuitModel toCircuitModel(CircuitDTORequest cdr) {
        try {
            final var cm = new CircuitModel();
            cm.setName(cdr.getName());
            cm.setCity(cdr.getCity());
            cm.setImage(cdr.getImage());
            cm.setLaps(cdr.getLaps());
            cm.setLength(cdr.getLength());
            cm.setSlow_turns(cdr.getSlow_turns());
            cm.setMedium_turns(cdr.getMedium_turns());
            cm.setFast_turns(cdr.getFast_turns());
            return cm;

        } catch (NullPointerException e) {
            return null;
        }
    }
}
