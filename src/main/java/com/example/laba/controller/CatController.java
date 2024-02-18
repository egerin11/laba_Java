package com.example.laba.controller;


import com.example.laba.service.CatService;
import com.example.laba.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cats")
public class CatController {

    private final MainService mainService;

    @Autowired
    public CatController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/{action}")
    public String getRandomCatFact(@PathVariable String action) {
        return mainService.getInf(action);
    }
}
