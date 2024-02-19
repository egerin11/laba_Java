package com.example.laba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MainController {
    private final CatController catController;

    @Autowired
    public MainController(CatController catController){
        this.catController=catController;
    }
    @GetMapping("/cats/{action}")
    public String getInf(@PathVariable String action){
        return catController.getRandomCatFact(action);
    }
//
}
