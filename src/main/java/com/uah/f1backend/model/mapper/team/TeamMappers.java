package com.uah.f1backend.model.mapper.team;

import com.uah.f1backend.model.dto.team.TeamDTOResponseRequest;
import com.uah.f1backend.model.TeamModel;

import java.util.List;

public class TeamMappers {

    // Maps a given TeamModel object to TeamDTOResponse object
    public static TeamDTOResponseRequest teamDTOResponseRequestMapper(TeamModel tm){
        try {
            return new TeamDTOResponseRequest(tm.getName(), tm.getLogo(), tm.getTwitter());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static TeamModel teamModelMapper(TeamDTOResponseRequest tm) {
        try {
            final var teamModel = new TeamModel();
            teamModel.setName(tm.getName());
            teamModel.setLogo(tm.getLogo());
            teamModel.setTwitter(tm.getTwitter());
            return teamModel;
        } catch (NullPointerException e){
            return null;
        }
    }

    // Maps a given List<TeamModel> object to List<TeamDTOResponse> object
    public static List<TeamDTOResponseRequest> teamListDTOResponseRequestMapper(List<TeamModel> tml){
        return tml.stream().map(TeamMappers::teamDTOResponseRequestMapper).toList();
    }
}
