package com.example.laba.controller;

import com.example.laba.model.Breed;
import com.example.laba.model.CatFact;
import com.example.laba.repository.CatInfRepository;
import com.example.laba.repository.CatRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MainController {
    private final CatController catController;
    private final CatRepository catRepository;
    private final CatInfRepository catInfRepository;


    @GetMapping
    public List<Breed> getAll() {
        assert catRepository != null;
        return catRepository.getCats();
    }

    @GetMapping("/facts")
    public List<CatFact> getAllFact() {
        assert catInfRepository != null;
        return catInfRepository.getFacts();
    }

    @GetMapping("/cats/{action}")
    public JsonNode getInf(@PathVariable String action) {

        assert catController != null;
        return catController.getRandomCatFact(action);
    }

}
