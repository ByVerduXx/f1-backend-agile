package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.race.RaceDTORequest;
import com.uah.f1backend.model.dto.race.RaceDTOResponse;
import com.uah.f1backend.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("races")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RaceRestController {
    private final RaceService raceService;

    @GetMapping
    public ResponseEntity<List<RaceDTOResponse>> obtainAll() {
        return ResponseEntity.ok(raceService.getAllRaces());
    }

    @PostMapping
    public ResponseEntity<RaceDTOResponse> saveCRace(@RequestBody RaceDTORequest raceDTORequest) {
        return new ResponseEntity<>(raceService.saveRace(raceDTORequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RaceDTOResponse> updateRace(
            @PathVariable Integer id, @RequestBody RaceDTORequest raceDTORequest) {
        return new ResponseEntity<>(raceService.updateRace(id, raceDTORequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRace(@PathVariable Integer id) {
        return ResponseEntity.ok(raceService.deleteRace(id));
    }
}
