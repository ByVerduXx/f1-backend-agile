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
        return teamService.getTeamByName(name);
    }

    @PutMapping()
    public TeamDTOResponseRequest insertTeam(@RequestBody TeamDTOResponseRequest team){
        return teamService.insertTeam(team);
    }

    @DeleteMapping()
    public DeletedTeamDTOResponse deleteTeam(@RequestParam String name){
        return teamService.deleteTeam(name);
    }
}
