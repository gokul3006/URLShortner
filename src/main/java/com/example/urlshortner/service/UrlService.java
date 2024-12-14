package com.example.urlshortner.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urlshortner.entity.Url;
import com.example.urlshortner.repository.UrlRepository;

@Service
public class UrlService {

private final UrlRepository urlRepository;
private static final String BASE_URL = "http://localhost:8080/";
public UrlService(UrlRepository urlRepository) {
    this.urlRepository = urlRepository;  // Initialize the final field via the constructor
}
public Optional<String> getURL(String url)
{
	Optional<Url> urlObj=urlRepository.findByModifiedURL(BASE_URL+url);
	if(urlObj.isPresent())return Optional.of(urlObj.get().getOriginalURL());
	return null;
}
public Optional<String> generateUrl(String url) throws NoSuchAlgorithmException
{
	 MessageDigest digest = MessageDigest.getInstance("SHA-256");

     // Generate the hash for the original URL
     byte[] hashBytes = digest.digest(url.getBytes());

     // Convert the first 8 bytes of the hash into a hexadecimal string
     StringBuilder shortCode = new StringBuilder();
     for (int i = 0; i < 10; i++) {
         shortCode.append(String.format("%02x", hashBytes[i]));
     }
     String newUrl=new String(BASE_URL+shortCode.toString());
     Optional<Url> optUrl=urlRepository.findByModifiedURL(newUrl);
     if(optUrl.isEmpty())
     {
     Url urlObj=Url.builder()
    		 .originalURL(url)
    		 .modifiedURL(newUrl)
    		 .build();
    urlRepository.saveAndFlush(urlObj);
     }
    		 
	return Optional.ofNullable(BASE_URL+shortCode.toString());
}
}
