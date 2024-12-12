package com.example.urlshortner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.urlshortner.entity.Url;

@Repository
public interface UrlRepository  extends JpaRepository<Url,Long> {
	 Optional<Url> findByModifiedURL(String modifiedURL);
}
