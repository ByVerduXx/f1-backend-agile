package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpStatus;
import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
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
        final var result = TeamMappers.teamDTOResponseRequestMapper(teamModelRepository.findByName(name));
        if (result == null) {
            throw new HttpStatus.TeamDoesntExistException();
        } else {
            return result;
        }
    }

    // Add new team in the db
    public TeamDTOResponseRequest insertTeam(TeamDTOResponseRequest team){
        final var teamModel = TeamMappers.teamModelMapper(team);
        if (teamModel == null) {
            throw new HttpStatus.ResourceNotSavedException();
        }
        return TeamMappers.teamDTOResponseRequestMapper(teamModelRepository.save(teamModel));
    }

    // Remove team from db given its name
    public DeletedTeamDTOResponse deleteTeam(String name){
        final var teamModel = teamModelRepository.findByName(name);
        if (teamModel != null) {
            teamModelRepository.delete(teamModel);
            return new DeletedTeamDTOResponse("Team deleted", name);
        } else {
            throw new HttpStatus.TeamDoesntExistException();
        }
    }
}
