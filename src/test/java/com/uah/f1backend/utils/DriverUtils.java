package com.uah.f1backend.utils;

import static com.uah.f1backend.utils.CountryUtils.*;
import static com.uah.f1backend.utils.TeamUtils.*;

import com.uah.f1backend.model.DriverModel;
import com.uah.f1backend.model.dto.driver.DeletedDriverDTOResponse;
import com.uah.f1backend.model.dto.driver.DriverDTORequest;
import com.uah.f1backend.model.dto.driver.DriverDTOResponse;
import java.util.ArrayList;
import java.util.List;

public class DriverUtils {
    public static DriverDTORequest dummyDriverDTORequest() {
        return new DriverDTORequest("name", "lastname", "nl", 1, "photo.png", "@twitter", 1, 1);
    }

    public static DriverDTORequest dummyDriverDTORequestOnIT(Integer idCountry, Integer idTeam) {
        return new DriverDTORequest("name", "lastname", "nl", 1, "photo.png", "@twitter", idCountry, idTeam);
    }

    public static DriverDTORequest dummyDriverDTONullTeamRequest() {
        return new DriverDTORequest("name", "lastname", "nl", 1, "photo.png", "@twitter", 1, null);
    }

    public static DriverDTORequest dummy2DriverDTORequestOnIT(Integer idCountry, Integer idTeam) {
        return new DriverDTORequest("name2", "lastname2", "nl2", 1, "photo2.png", "@twitter", idCountry, idTeam);
    }

    public static DriverDTORequest dummy3DriverDTORequest() {
        return new DriverDTORequest("name3", "lastname3", "nl3", 3, "photo3.png", "@twitter", 1, 1);
    }

    public static DriverDTOResponse dummyDriverDTOResponse() {
        return new DriverDTOResponse(
                1, "name", "lastname", "nl", 1, "photo.png", "@twitter", dummyCountryDTOResponse(1), 1);
    }

    public static DriverDTOResponse dummyDriverDTOResponseOnIT(Integer idCountry, Integer idTeam) {
        return new DriverDTOResponse(1, "name", "lastname", "nl", 1, "photo.png", "@twitter", dummyCountryDTOResponse(idCountry), idTeam);
    }

    public static DriverDTOResponse dummy2DriverDTOResponseOnIT(Integer idCountry, Integer idTeam) {
        return new DriverDTOResponse(1, "name2", "lastname2", "nl2", 1, "photo2.png", "@twitter", dummyCountryDTOResponse(idCountry), idTeam);
    }

    public static DriverDTOResponse dummyDriverDTONullTeamResponse() {
        return new DriverDTOResponse(
                1, "name", "lastname", "nl", 1, "photo.png", "@twitter", dummyCountryDTOResponse(1), null);
    }

    public static List<DriverDTOResponse> dummyListDriverDTOResponse() {
        final var dummyList = new ArrayList<DriverDTOResponse>();
        for (var i = 0; i < 3; i++) {
            dummyList.add(new DriverDTOResponse(
                    i,
                    "name" + i,
                    "lastname" + i,
                    "nl" + i,
                    i,
                    String.format("photo%s.png", i),
                    "@twitter" + i,
                    dummyCountryDTOResponse(1),
                    1));
        }
        return dummyList;
    }

    public static DriverModel dummyDriverModel() {
        final var dm = new DriverModel();
        dm.setId(1);
        dm.setName("name");
        dm.setLastName("lastname");
        dm.setInitial("nl");
        dm.setDorsal(1);
        dm.setPhoto("photo.png");
        dm.setTwitter("@twitter");
        dm.setCountry(dummyCountryModel());
        dm.setTeam(dummyTeamModel());
        return dm;
    }

    public static DriverModel dummyDriverNullTeamModel() {
        final var dm = new DriverModel();
        dm.setId(1);
        dm.setName("name");
        dm.setLastName("lastname");
        dm.setInitial("nl");
        dm.setDorsal(1);
        dm.setPhoto("photo.png");
        dm.setTwitter("@twitter");
        dm.setCountry(dummyCountryModel());
        dm.setTeam(null);
        return dm;
    }

    public static DriverModel dummy2DriverModel() {
        final var dm = new DriverModel();
        dm.setId(2);
        dm.setName("name2");
        dm.setLastName("lastname2");
        dm.setInitial("nl2");
        dm.setDorsal(3);
        dm.setPhoto("photo2.png");
        dm.setTwitter("@twitter");
        dm.setCountry(dummyCountryModel());
        dm.setTeam(dummyTeamModel());
        return dm;
    }

    public static List<DriverModel> dummyListDriverModel() {
        final var dummyList = new ArrayList<DriverModel>();
        for (var i = 0; i < 3; i++) {
            final var dm = new DriverModel();
            dm.setId(i);
            dm.setName("name" + i);
            dm.setLastName("lastname" + i);
            dm.setInitial("nl" + i);
            dm.setDorsal(i);
            dm.setPhoto(String.format("photo%s.png", i));
            dm.setTwitter("@twitter" + i);
            dm.setCountry(dummyCountryModel());
            dm.setTeam(dummyTeamModel());
            dummyList.add(dm);
        }
        return dummyList;
    }

    public static DeletedDriverDTOResponse dummyDeletedDriverDTOResponse() {
        return new DeletedDriverDTOResponse("Driver with id 1 deleted", 1);
    }
}
