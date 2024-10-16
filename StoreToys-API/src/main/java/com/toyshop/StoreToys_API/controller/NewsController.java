package com.toyshop.StoreToys_API.controller;

import com.toyshop.StoreToys_API.DTOs.request.NewsRequest;
import com.toyshop.StoreToys_API.DTOs.respone.APIRespone;
import com.toyshop.StoreToys_API.model.News;
import com.toyshop.StoreToys_API.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("")
    public ResponseEntity<?> getAllNews() {
        return ResponseEntity.status(200).body(new APIRespone<>(newsService.getAllNews(),  "Request successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable String id) {
        return ResponseEntity.status(200)
                .body(new APIRespone<>(newsService.getNewsById(id), "Request successfully"));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> createNews(@ModelAttribute NewsRequest newsRequest) {
        return ResponseEntity.status(201)
                .body(new APIRespone<>(newsService.createNews(newsRequest), "News created successfully"));
    }
}
