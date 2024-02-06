package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.survey.SurveyDTORequest;
import com.uah.f1backend.model.dto.survey.SurveyDTOResponse;
import com.uah.f1backend.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("surveys")
@RequiredArgsConstructor
public class SurveyRestController {
    private final SurveyService surveyService;

    @GetMapping
    public ResponseEntity<List<SurveyDTOResponse>> obtainAll() {
        return ResponseEntity.ok(surveyService.obtainAllSurveys());
    }

    @GetMapping("{id}")
    public ResponseEntity<SurveyDTOResponse> obtainSurveyById(@PathVariable Integer id) {
        return ResponseEntity.ok(surveyService.obtainSurveyById(id));
    }

    @PostMapping
    public ResponseEntity<SurveyDTOResponse> insertSurvey(@RequestBody SurveyDTORequest survey) {
        return new ResponseEntity<>(surveyService.insertSurvey(survey), CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<SurveyDTOResponse> updateSurvey(@PathVariable Integer id, @RequestBody SurveyDTORequest survey) {
        return new ResponseEntity<>(surveyService.updateSurvey(id, survey), CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable Integer id) {
        return ResponseEntity.ok(surveyService.deleteSurvey(id));
    }
}
