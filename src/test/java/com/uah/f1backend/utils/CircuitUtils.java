package com.uah.f1backend.utils;

import static com.uah.f1backend.utils.CountryUtils.dummyCountryDTOResponse;
import static com.uah.f1backend.utils.CountryUtils.dummyCountryModel;

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
        cm.setCountry(dummyCountryModel());
        cm.setImage("image.png");
        cm.setLaps(1);
        cm.setLength(1);
        cm.setSlowTurns(1);
        cm.setMediumTurns(1);
        cm.setFastTurns(1);
        return cm;
    }

    public static CircuitModel dummyCircuitModel2() {
        final var cm = new CircuitModel();
        cm.setId(1);
        cm.setName("name2");
        cm.setCity("city2");
        cm.setCountry(dummyCountryModel());
        cm.setImage("image2.png");
        cm.setLaps(2);
        cm.setLength(2);
        cm.setSlowTurns(2);
        cm.setMediumTurns(2);
        cm.setFastTurns(2);
        return cm;
    }

    public static List<CircuitModel> dummyListCircuitModel() {
        final var dummyList = new ArrayList<CircuitModel>();
        for (var i = 1; i <= 3; i++) {
            final var cm = new CircuitModel();
            cm.setId(i);
            cm.setName("name" + i);
            cm.setCity("city");
            cm.setCountry(dummyCountryModel());
            cm.setImage("image.png");
            cm.setLaps(i);
            cm.setLength(i);
            cm.setSlowTurns(i);
            cm.setMediumTurns(i);
            cm.setFastTurns(i);
            dummyList.add(cm);
        }
        return dummyList;
    }

    public static List<CircuitDTOResponse> dummyListCircuitDTOResponse() {
        final var dummyList = new ArrayList<CircuitDTOResponse>();
        for (var i = 1; i <= 3; i++) {
            dummyList.add(new CircuitDTOResponse(
                    i, "name" + i, "city", dummyCountryDTOResponse(1), "image.png", i, i, i, i, i));
        }
        return dummyList;
    }

    public static CircuitDTOResponse dummyCircuitDTOResponse() {

        return new CircuitDTOResponse(1, "name", "city", dummyCountryDTOResponse(1), "image.png", 1, 1, 1, 1, 1);
    }

    public static CircuitDTOResponse dummyCircuitDTOResponseOnIT(Integer idCountry) {
        return new CircuitDTOResponse(
                1, "name", "city", dummyCountryDTOResponse(idCountry), "image.png", 1, 1, 1, 1, 1);
    }

    public static CircuitDTORequest dummyCircuitDTORequest() {
        return new CircuitDTORequest("name", "city", 1, "image.png", 1, 1, 1, 1, 1);
    }

    public static CircuitDTORequest dummyCircuitDTORequestOnIT(Integer idCountry) {
        return new CircuitDTORequest("name", "city", idCountry, "image.png", 1, 1, 1, 1, 1);
    }

    public static CircuitDTORequest dummyCircuitDTORequest2() {
        return new CircuitDTORequest("name2", "city2", 1, "image2.png", 2, 2, 2, 2, 2);
    }

    public static CircuitDTORequest dummyCircuitDTORequest2OnIT(Integer idCountry) {
        return new CircuitDTORequest("name2", "city2", idCountry, "image2.png", 2, 2, 2, 2, 2);
    }

    public static CircuitDTOResponse dummyCircuitDTOResponse2() {
        return new CircuitDTOResponse(1, "name2", "city2", dummyCountryDTOResponse(1), "image2.png", 2, 2, 2, 2, 2);
    }

    public static CircuitDTOResponse dummyCircuitDTOResponse2OnIT(Integer idCountry) {
        return new CircuitDTOResponse(
                1, "name2", "city2", dummyCountryDTOResponse(idCountry), "image2.png", 2, 2, 2, 2, 2);
    }
}
