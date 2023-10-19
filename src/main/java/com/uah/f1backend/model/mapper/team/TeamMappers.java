package com.uah.f1backend.model.mapper.team;

import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
import java.util.List;

public class TeamMappers {

    // Maps a given TeamModel object to TeamDTORequest object
    public static TeamDTORequest toTeamDTORequestMapper(TeamModel tm) {
        try {
            return new TeamDTORequest(tm.getName(), tm.getLogo(), tm.getTwitter());
        } catch (NullPointerException e) {
            return null;
        }
    }

    // Maps a given List<TeamModel> object to List<TeamDTORequest> object
    public static List<TeamDTORequest> toTeamListDTORequestMapper(List<TeamModel> tml) {
        return tml.stream().map(TeamMappers::toTeamDTORequestMapper).toList();
    }

    // Maps a given TeamModel object to TeamDTOResponse object
    public static TeamDTOResponse toTeamDTOResponseMapper(TeamModel tm) {
        try {
            return new TeamDTOResponse(tm.getId(), tm.getName(), tm.getLogo(), tm.getTwitter());
        } catch (NullPointerException e) {
            return null;
        }
    }

    // Maps a given List<TeamModel> object to List<TeamDTOResponse> object
    public static List<TeamDTOResponse> toTeamListDTOResponseMapper(List<TeamModel> tml) {
        return tml.stream().map(TeamMappers::toTeamDTOResponseMapper).toList();
    }

    // Maps a given TeamDTORequest object to TeamModel object
    public static TeamModel toTeamModelMapper(TeamDTOResponse tm) {
        try {
            final var teamModel = new TeamModel();
            teamModel.setId(tm.getId());
            teamModel.setName(tm.getName());
            teamModel.setLogo(tm.getLogo());
            teamModel.setTwitter(tm.getTwitter());
            return teamModel;
        } catch (NullPointerException e) {
            return null;
        }
    }

    // Maps a given TeamDTORequest object to TeamModel object
    public static TeamModel toTeamModelMapper(TeamDTORequest tm) {
        try {
            final var teamModel = new TeamModel();
            teamModel.setName(tm.getName());
            teamModel.setLogo(tm.getLogo());
            teamModel.setTwitter(tm.getTwitter());
            return teamModel;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
