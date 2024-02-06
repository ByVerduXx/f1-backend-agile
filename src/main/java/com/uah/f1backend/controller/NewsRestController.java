package com.uah.f1backend.controller;

import com.uah.f1backend.model.dto.news.NewsDTORequest;
import com.uah.f1backend.model.dto.news.NewsDTOResponse;
import com.uah.f1backend.service.NewsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("news")
@RequiredArgsConstructor
public class NewsRestController {
    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsDTOResponse>> obtainAll() {
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("{id}")
    public ResponseEntity<NewsDTOResponse> obtainById(@PathVariable Integer id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }

    @PostMapping
    public ResponseEntity<NewsDTOResponse> saveNews(@RequestBody NewsDTORequest newsDTORequest) {
        return new ResponseEntity<>(newsService.saveNews(newsDTORequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDTOResponse> updateNews(
            @PathVariable Integer id, @RequestBody NewsDTORequest newsDTORequest) {
        return new ResponseEntity<>(newsService.updateNews(id, newsDTORequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNews(@PathVariable Integer id) {
        return ResponseEntity.ok(newsService.deleteNews(id));
    }
}
