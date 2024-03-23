package com.example.laba.model.dto;

import java.util.HashSet;
import java.util.Set;

public class CatDTO {
    private Long id;
    private String name;
    private Integer age;
    private Set<CatFactDto> facts = new HashSet<>();
    private Set<OwnerDto> owners = new HashSet<>();

    // Getters and Setters
}