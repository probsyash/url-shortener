package com.yash.urlshortener.controller;
import com.yash.urlshortener.service.UrlService;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // POST /api/shorten
    @PostMapping("/api/shorten")
    public String shortenUrl(@RequestBody String originalUrl) {
        return urlService.shortenUrl(originalUrl);
    }

    // GET /{code}
    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String originalUrl = urlService.getOriginalUrl(code);
        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(originalUrl))
        .build();
    }
}