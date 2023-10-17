package com.uah.f1backend.service;

import com.uah.f1backend.model.TeamModel;
import com.uah.f1backend.model.dto.team.TeamDTOResponseRequest;
import com.uah.f1backend.model.mapper.team.TeamMappers;
import com.uah.f1backend.repository.TeamModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamModelRepository teamModelRepository;

    // Retrieve all the teams in the db
    public List<TeamDTOResponseRequest> getAllTeams(){
        return TeamMappers.teamListDTOResponseRequestMapper(teamModelRepository.findAll());
    }

    // Retrieve the team matching the given name
    public TeamDTOResponseRequest getTeamByName(String name){
        return TeamMappers.teamDTOResponseRequestMapper(teamModelRepository.findByName(name));
    }

    public TeamDTOResponseRequest insertTeam(TeamDTOResponseRequest team){
        final var teamModel = TeamMappers.teamModelMapper(team);
        if (teamModel == null) return null;
        return TeamMappers.teamDTOResponseRequestMapper(teamModelRepository.save(teamModel));
    }
}
