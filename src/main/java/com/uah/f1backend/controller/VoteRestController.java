package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.vote.VoteDTORequest;
import com.uah.f1backend.model.dto.vote.VoteDTOResponse;
import com.uah.f1backend.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("votes")
@RequiredArgsConstructor
public class VoteRestController {
    private final VoteService voteService;

    @GetMapping
    public ResponseEntity<List<VoteDTOResponse>> obtainAll() {
        return ok(voteService.obtainAllVotes());
    }

    @GetMapping("{id}")
    public ResponseEntity<VoteDTOResponse> obtainVoteById(@PathVariable Integer id) {
        return ok(voteService.obtainVoteById(id));
    }

    @PostMapping
    public ResponseEntity<VoteDTOResponse> insertVote(@RequestBody VoteDTORequest voteDTORequest) {
        return new ResponseEntity<>(voteService.insertVote(voteDTORequest), CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<VoteDTOResponse> updateVote(@PathVariable Integer id, @RequestBody VoteDTORequest voteDTORequest) {
        return new ResponseEntity<>(voteService.updateVote(id, voteDTORequest), CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVote(@PathVariable Integer id) {
        return ResponseEntity.ok(voteService.deleteVote(id));
    }
}
