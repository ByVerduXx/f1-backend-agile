package com.uah.f1backend.utils;

import com.uah.f1backend.model.CarModel;
import com.uah.f1backend.model.dto.car.CarDTORequest;
import com.uah.f1backend.model.dto.car.CarDTOResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarUtils {

    public static List<CarModel> dummyListCarModel() {
        final var dummyList = new ArrayList<CarModel>();
        for (var i = 0; i <= 3; i++) {
            final var tm = new CarModel();
            tm.setId(i);
            tm.setName("name" + i);
            tm.setCode(String.format("code%s", i));
            tm.setErsGainSlow(BigDecimal.valueOf(3.4));
            tm.setErsGainMedium(BigDecimal.valueOf(2.4));
            tm.setErsGainFast(BigDecimal.valueOf(6.4));
            tm.setConsumption(BigDecimal.valueOf(1.4));
            tm.setTeam(TeamUtils.dummyTeamModel());
            dummyList.add(tm);
        }
        return dummyList;
    }

    public static List<CarDTOResponse> dummyListCarDTOResponse() {
        final var dummyList = new ArrayList<CarDTOResponse>();
        for (var i = 0; i <= 3; i++) {
            dummyList.add(new CarDTOResponse(
                    i,
                    "name" + i,
                    String.format("code%s", i),
                    BigDecimal.valueOf(3.4),
                    BigDecimal.valueOf(2.4),
                    BigDecimal.valueOf(6.4),
                    BigDecimal.valueOf(1.4),
                    TeamUtils.dummyTeamModel().getName()));
        }
        return dummyList;
    }

    public static CarModel dummyCarModel() {
        final var tm = new CarModel();
        tm.setId(1);
        tm.setName("name");
        tm.setCode("code");
        tm.setErsGainSlow(BigDecimal.valueOf(3.4));
        tm.setErsGainMedium(BigDecimal.valueOf(2.4));
        tm.setErsGainFast(BigDecimal.valueOf(6.4));
        tm.setConsumption(BigDecimal.valueOf(1.4));
        tm.setTeam(TeamUtils.dummyTeamModel());
        return tm;
    }

    public static CarModel dummyCarModel2() {
        final var tm = new CarModel();
        tm.setId(2);
        tm.setName("name2");
        tm.setCode("code2");
        tm.setErsGainSlow(BigDecimal.valueOf(3.4));
        tm.setErsGainMedium(BigDecimal.valueOf(2.4));
        tm.setErsGainFast(BigDecimal.valueOf(6.4));
        tm.setConsumption(BigDecimal.valueOf(1.4));
        tm.setTeam(TeamUtils.dummyTeamModel());
        return tm;
    }

    public static CarDTOResponse dummyCarDTOResponse() {
        return new CarDTOResponse(
                1,
                "name",
                "code",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(1.4),
                TeamUtils.dummyTeamModel().getName());
    }

    public static CarDTOResponse dummyCarDTOResponse2() {
        return new CarDTOResponse(
                2,
                "name2",
                "code2",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(1.4),
                TeamUtils.dummyTeamModel().getName());
    }

    public static CarDTORequest dummyCarDTORequest() {
        return new CarDTORequest(
                "name",
                "code",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(1.4));
    }

    public static CarDTORequest dummyCarDTORequest2() {
        return new CarDTORequest(
                "name2",
                "code2",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(1.4));
    }

    public static CarDTORequest dummyCarDTORequestBadName() {
        return new CarDTORequest(
                "",
                "code",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(1.4));
    }

    public static CarDTORequest dummyCarDTORequestBadCode() {
        return new CarDTORequest(
                "name",
                "23",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(1.4));
    }

    public static CarDTORequest dummyCarDTORequestBadErs() {
        return new CarDTORequest(
                "name",
                "code",
                BigDecimal.valueOf(-3.4),
                BigDecimal.valueOf(-2.4),
                BigDecimal.valueOf(-6.4),
                BigDecimal.valueOf(1.4));
    }

    public static CarDTORequest dummyCarDTORequestBadConsumption() {
        return new CarDTORequest(
                "name",
                "code",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(-1.4));
    }

    public static CarDTORequest dummyCarDTORequestIT(Integer id) {
        return new CarDTORequest(
                "name",
                "code",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(1.4));
    }

    public static CarDTORequest dummyCarDTORequestIT2(Integer id) {
        return new CarDTORequest(
                "Pedro de los cojoens",
                "code2",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(1.4));
    }

    public static CarDTOResponse dummyCarDTOResponseIT(String teamName) {
        return new CarDTOResponse(
                1,
                "name",
                "code",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(1.4),
                teamName);
    }

    public static CarDTOResponse dummyCarDTOResponseIT2(String teamName) {
        return new CarDTOResponse(
                1,
                "Pedro de los cojoens",
                "code2",
                BigDecimal.valueOf(3.4),
                BigDecimal.valueOf(2.4),
                BigDecimal.valueOf(6.4),
                BigDecimal.valueOf(1.4),
                teamName);
    }
}
