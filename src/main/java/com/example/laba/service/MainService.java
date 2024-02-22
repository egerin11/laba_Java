package com.example.laba.service;


import lombok.AllArgsConstructor;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MainService {
    private final CatService catService;

    public String getInf(String action) {
        return catService.getInf(action);
    }

    @SneakyThrows
    public void catInf(String action) {
        catService.saveInfCat(action);
    }



}
