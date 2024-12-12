package com.example.urlshortner.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlshortner.dto.UrlDto;
import com.example.urlshortner.service.UrlService;

@RestController
@RequestMapping("/url")
public class UrlController {
@Autowired
private  UrlService urlService;

@PostMapping("/short")
public ResponseEntity<String> generateShortURL(@RequestBody UrlDto url) throws NoSuchAlgorithmException
{
	return urlService.generateUrl(url.getUrl())
			.map(ResponseEntity::ok)
			.orElseGet(()->ResponseEntity.status(400).body("not able to process"));
}
}
