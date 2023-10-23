package com.uah.f1backend.utils;

import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
import java.util.ArrayList;
import java.util.List;

public class TeamUtils {

    public static TeamDTORequest dummyTeamDTORequest() {
        return new TeamDTORequest("name", "logo.png", "@twitter");
    }

    public static TeamDTORequest dummyTeamDTORequest2() {
        return new TeamDTORequest("name2", "logo2.png", "@twitter2");
    }

    public static TeamDTOResponse dummyTeamDTOResponse() {
        return new TeamDTOResponse(1, "name", "logo.png", "@twitter");
    }

    public static TeamDTOResponse dummyTeamDTOResponseOnIT(Integer id) {
        return new TeamDTOResponse(id, "name", "logo.png", "@twitter");
    }

    public static TeamDTOResponse dummyTeamDTOResponseOnUpdate() {
        return new TeamDTOResponse(1, "name2", "logo2.png", "@twitter2");
    }

    public static TeamDTOResponse dummyTeamDTOResponseOnUpdateIT(Integer id) {
        return new TeamDTOResponse(id, "name2", "logo2.png", "@twitter2");
    }

    public static List<TeamDTOResponse> dummyListTeamDTOResponse() {
        final var dummyList = new ArrayList<TeamDTOResponse>();
        for (var i = 0; i <= 3; i++) {
            dummyList.add(new TeamDTOResponse(i, "name" + i, String.format("logo%s.png", i), "@twitter" + i));
        }
        return dummyList;
    }

    public static TeamModel dummyTeamModel() {
        final var tm = new TeamModel();
        tm.setId(1);
        tm.setName("name");
        tm.setLogo("logo.png");
        tm.setTwitter("@twitter");
        return tm;
    }

    public static List<TeamModel> dummyListTeamModel() {
        final var dummyList = new ArrayList<TeamModel>();
        for (var i = 0; i <= 3; i++) {
            final var tm = new TeamModel();
            tm.setId(i);
            tm.setName("name" + i);
            tm.setLogo(String.format("logo%s.png", i));
            tm.setTwitter("@twitter" + i);
            dummyList.add(tm);
        }
        return dummyList;
    }
}
