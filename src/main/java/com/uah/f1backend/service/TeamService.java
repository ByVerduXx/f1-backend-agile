package com.uah.f1backend.service;

import com.uah.f1backend.configuration.HttpStatus;
import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
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
    public List<TeamDTOResponse> getAllTeams(){
        return TeamMappers.toTeamListDTOResponseMapper(teamModelRepository.findAll());
    }

    // Retrieve the team matching the given name
    public TeamDTOResponse getTeamByName(String name){
        final var team = teamModelRepository.findByName(name)
                .orElseThrow(HttpStatus.TeamDoesntExistException::new);
        return TeamMappers.toTeamDTOResponseMapper(team);
    }

    // Retrieve the team matching the given id
    public TeamDTOResponse getTeamById(Integer id){
        final var team = teamModelRepository.findById(Long.valueOf(id))
                .orElseThrow(HttpStatus.TeamDoesntExistException::new);
        return TeamMappers.toTeamDTOResponseMapper(team);
    }

    // Add new team in the db
    public TeamDTOResponse insertTeam(TeamDTORequest team){
        final var teamModel = TeamMappers.toTeamModelMapper(team);
        if (teamModel == null) {
            throw new HttpStatus.ResourceNotSavedException();
        }
        return TeamMappers.toTeamDTOResponseMapper(teamModelRepository.save(teamModel));
    }

    // Remove team from db given its name
    public DeletedTeamDTOResponse deleteTeamByName(String name){
        final var team = teamModelRepository.findByName(name)
                .orElseThrow(HttpStatus.TeamDoesntExistException::new);
        teamModelRepository.delete(team);
        return new DeletedTeamDTOResponse("Team deleted", name);
    }

    // Remove team from db given its id
    public DeletedTeamDTOResponse deleteTeamById(Integer id){
        final var team = teamModelRepository.findById(Long.valueOf(id))
                .orElseThrow(HttpStatus.TeamDoesntExistException::new);
        teamModelRepository.deleteById(Long.valueOf(id));
        return new DeletedTeamDTOResponse("Team deleted", team.getName());
    }
}
