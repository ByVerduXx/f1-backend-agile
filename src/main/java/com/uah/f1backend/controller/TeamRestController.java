package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.team.DeletedTeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDTORequest;
import com.uah.f1backend.model.dto.team.TeamDTOResponse;
import com.uah.f1backend.model.dto.team.TeamDetailDTOResponse;
import com.uah.f1backend.service.TeamService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("teams")
@RequiredArgsConstructor
public class TeamRestController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<TeamDTOResponse>> obtainAll() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    @GetMapping(params = "name")
    public ResponseEntity<TeamDTOResponse> obtainByName(@RequestParam String name) {
        return ResponseEntity.ok(teamService.getTeamByName(name));
    }

    @GetMapping("manager")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<TeamDetailDTOResponse> obtainManagerTeam() {
        return ResponseEntity.ok(teamService.getTeamByUserAuthenticated());
    }

    @PostMapping("addManager")
    @Secured("ROLE_MANAGER")
    public ResponseEntity<TeamDetailDTOResponse> addManagerToTeam(@RequestBody Integer id) {
        return ResponseEntity.ok(teamService.addManagerToTeam(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<TeamDTOResponse> obtainById(@PathVariable Integer id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @GetMapping("{id}/detail")
    public ResponseEntity<TeamDetailDTOResponse> obtainDetailById(@PathVariable Integer id) {
        return ResponseEntity.ok(teamService.getTeamDetailById(id));
    }

    @PostMapping
    @Secured("ROLE_MANAGER")
    public ResponseEntity<TeamDTOResponse> insertTeam(@RequestBody TeamDTORequest team) {
        return new ResponseEntity<>(teamService.insertTeam(team), HttpStatus.CREATED);
    }

    @DeleteMapping(params = "name")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DeletedTeamDTOResponse> deleteTeamByName(@RequestParam String name) {
        return ResponseEntity.ok(teamService.deleteTeamByName(name));
    }

    @DeleteMapping("{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DeletedTeamDTOResponse> deleteTeamById(@PathVariable Integer id) {
        return ResponseEntity.ok(teamService.deleteTeamById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<TeamDTOResponse> updateTeamById(@PathVariable Integer id, @RequestBody TeamDTORequest team) {
        return new ResponseEntity<>(teamService.updateTeamById(id, team), HttpStatus.CREATED);
    }
}
