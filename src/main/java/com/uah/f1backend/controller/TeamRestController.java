package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
import com.uah.f1backend.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("teams")
@RequiredArgsConstructor
public class TeamRestController {

    private final TeamService teamService;

    @GetMapping()
    public ResponseEntity<List<TeamDTOResponse>> getTeams(){
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping(params = "name")
    public ResponseEntity<TeamDTOResponse> getTeams(@RequestParam String name){
        return ResponseEntity.ok(teamService.getTeamByName(name));
    }

    @GetMapping(params = "id")
    public ResponseEntity<TeamDTOResponse> getTeams(@RequestParam Integer id){
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @PostMapping()
    public ResponseEntity<TeamDTOResponse> insertTeam(@RequestBody TeamDTORequest team){
        return ResponseEntity.ok(teamService.insertTeam(team));
    }

    @DeleteMapping(params = "name")
    public ResponseEntity<DeletedTeamDTOResponse> deleteTeamByName(@RequestParam String name){
        return ResponseEntity.ok(teamService.deleteTeamByName(name));
    }
    @DeleteMapping(params = "id")
    public ResponseEntity<DeletedTeamDTOResponse> deleteTeamById(@RequestParam Integer id){
        return ResponseEntity.ok(teamService.deleteTeamById(id));
    }
}
