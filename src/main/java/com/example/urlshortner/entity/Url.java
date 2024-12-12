package com.example.urlshortner.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "urls") 
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder 
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String originalURL;
    
    private String modifiedURL;

	

    // Optional: Use @RequiredArgsConstructor if you want a constructor for required fields
    // @RequiredArgsConstructor
}
