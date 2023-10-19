package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
import com.uah.f1backend.service.TeamService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teams")
@RequiredArgsConstructor
public class TeamRestController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamDTOResponse>> getTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping(params = "name")
    public ResponseEntity<TeamDTOResponse> getTeams(@RequestParam String name) {
        return ResponseEntity.ok(teamService.getTeamByName(name));
    }

    @GetMapping("{id}")
    public ResponseEntity<TeamDTOResponse> getTeams(@PathVariable Integer id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @PostMapping
    public ResponseEntity<TeamDTOResponse> insertTeam(@RequestBody TeamDTORequest team) {
        return new ResponseEntity<>(teamService.insertTeam(team), HttpStatus.CREATED);
    }

    @DeleteMapping(params = "name")
    public ResponseEntity<DeletedTeamDTOResponse> deleteTeamByName(@RequestParam String name) {
        return ResponseEntity.ok(teamService.deleteTeamByName(name));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DeletedTeamDTOResponse> deleteTeamById(@PathVariable Integer id) {
        return ResponseEntity.ok(teamService.deleteTeamById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<TeamDTOResponse> updateTeamById(@PathVariable Integer id, @RequestBody TeamDTORequest team) {
        return new ResponseEntity<>(teamService.updateTeamById(id, team), HttpStatus.CREATED);
    }
}
