package com.example.laba.controller;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.repository.CatInfRepository;
import com.example.laba.repository.CatRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor

public class MainController {
    private final CatController catController;
    private final CatRepository catRepository;
    private final CatInfRepository catInfRepository;

    @GetMapping
    public List<Cat> getAll() {
        return catRepository.getCats();
    }

    @GetMapping("/facts")
    public List<CatFact> getAllFact() {
        return catInfRepository.getFacts();
    }

    @GetMapping("/cats/{action}")
    public String getInf(@PathVariable String action) {

        return catController.getRandomCatFact(action);
    }

}
