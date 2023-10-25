package com.uah.f1backend.utils;

import com.uah.f1backend.model.CircuitModel;
import com.uah.f1backend.model.dto.circuit.CircuitDTORequest;
import com.uah.f1backend.model.dto.circuit.CircuitDTOResponse;
import java.util.ArrayList;
import java.util.List;

public class CircuitUtils {
    public static CircuitModel dummyCircuitModel() {
        final var cm = new CircuitModel();
        cm.setId(1);
        cm.setName("name");
        cm.setCity("city");
        cm.setId_country(1);
        cm.setImage("image");
        cm.setLaps(1);
        cm.setLength(1);
        cm.setSlow_turns(1);
        cm.setMedium_turns(1);
        cm.setFast_turns(1);
        return cm;
    }

    public static CircuitModel dummyCircuitModel2() {
        final var cm = new CircuitModel();
        cm.setId(1);
        cm.setName("name2");
        cm.setCity("city2");
        cm.setId_country(2);
        cm.setImage("image2");
        cm.setLaps(2);
        cm.setLength(2);
        cm.setSlow_turns(2);
        cm.setMedium_turns(2);
        cm.setFast_turns(2);
        return cm;
    }

    public static List<CircuitModel> dummyListCircuitModel() {
        final var dummyList = new ArrayList<CircuitModel>();
        for (var i = 0; i <= 3; i++) {
            final var cm = new CircuitModel();
            cm.setId(i);
            cm.setName("name" + i);
            cm.setCity("city");
            cm.setId_country(i);
            cm.setImage("image");
            cm.setLaps(i);
            cm.setLength(i);
            cm.setSlow_turns(i);
            cm.setMedium_turns(i);
            cm.setFast_turns(i);
        }
        return dummyList;
    }

    public static CircuitDTOResponse dummyCircuitDTOResponse() {
        return new CircuitDTOResponse(1, "name", "city", 1, "image", 1, 1, 1, 1, 1);
    }

    public static CircuitDTORequest dummyCircuitDTORequest() {
        return new CircuitDTORequest("name", "city", 1, "image", 1, 1, 1, 1, 1);
    }

    public static CircuitDTORequest dummyCircuitDTORequest2() {
        return new CircuitDTORequest("name2", "city2", 2, "image2", 2, 2, 2, 2, 2);
    }

    public static CircuitDTOResponse dummyCircuitDTOResponse2() {
        return new CircuitDTOResponse(1, "name2", "city2", 2, "image2", 2, 2, 2, 2, 2);
    }
}
