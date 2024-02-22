package com.example.laba.controller;


import com.example.laba.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cats")
@AllArgsConstructor

public class CatController {

    private final MainService mainService;

    @GetMapping("/{action}")
    public String getRandomCatFact(@PathVariable String action) {
        mainService.catInf(action);
        return mainService.getInf(action);
    }
}
