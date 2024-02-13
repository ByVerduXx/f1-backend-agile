package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.race.RaceDTORequest;
import com.uah.f1backend.model.dto.race.RaceDTOResponse;
import com.uah.f1backend.service.RaceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("races")
@RequiredArgsConstructor
public class RaceRestController {
    private final RaceService raceService;

    @GetMapping
    public ResponseEntity<List<RaceDTOResponse>> obtainAll() {
        return ResponseEntity.ok(raceService.getAllRaces());
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<RaceDTOResponse> saveRace(@RequestBody RaceDTORequest raceDTORequest) {
        return new ResponseEntity<>(raceService.saveRace(raceDTORequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<RaceDTOResponse> updateRace(
            @PathVariable Integer id, @RequestBody RaceDTORequest raceDTORequest) {
        return new ResponseEntity<>(raceService.updateRace(id, raceDTORequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> deleteRace(@PathVariable Integer id) {
        return ResponseEntity.ok(raceService.deleteRace(id));
    }
}
