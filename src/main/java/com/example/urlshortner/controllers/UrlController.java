package com.example.urlshortner.controllers;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlshortner.dto.UrlDto;
import com.example.urlshortner.service.UrlService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/")
public class UrlController {
@Autowired
private  UrlService urlService;

@PostMapping("/url/short")
public ResponseEntity<String> generateShortURL(@RequestBody UrlDto url) throws NoSuchAlgorithmException
{
	return urlService.generateUrl(url.getUrl())
			.map(ResponseEntity::ok)
			.orElseGet(()->ResponseEntity.status(400).body("not able to process"));
}

@GetMapping("/{url}")
public ResponseEntity<?> getActualURL(@PathVariable("url") String url) {
    return urlService.getURL(url)
        .map(originalUrl -> ResponseEntity
                .status(HttpStatus.FOUND) // HTTP 302 Redirect
                .location(URI.create(originalUrl)) // Set Location header
                .build())
        .orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND) // HTTP 404 Not Found
                .build());
}


}
