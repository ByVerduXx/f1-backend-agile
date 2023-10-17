package com.uah.f1backend.controller;

import com.uah.f1backend.configuration.HttpStatus;
import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTOResponseRequest;
import com.uah.f1backend.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teams")
@RequiredArgsConstructor
public class TeamRestController {

    private final TeamService teamService;

    @GetMapping()
    public List<TeamDTOResponseRequest> getTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping(params = "name")
    public TeamDTOResponseRequest getTeams(@RequestParam String name){
        final var result = teamService.getTeamByName(name);
        if (result == null) {
            throw new HttpStatus.TeamDoesntExistException();
        }
        return result;
    }

    @PutMapping()
    public TeamDTOResponseRequest insertTeam(@RequestBody TeamDTOResponseRequest team){
        final var result = teamService.insertTeam(team);
        if (result == null) {
            throw new HttpStatus.ResourceNotSavedException();
        }
        return result;
    }

    @DeleteMapping()
    public DeletedTeamDTOResponse deleteTeam(@RequestParam String name){
        final var isDeleted = teamService.deleteTeam(name);
        if (isDeleted) {
            return new DeletedTeamDTOResponse("Team deleted", name);
        } else {
            throw new HttpStatus.TeamDoesntExistException();
        }

    }
}
