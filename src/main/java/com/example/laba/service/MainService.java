package com.example.laba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    private final CatService catService;

    @Autowired
    public MainService(CatService catService) {
        this.catService = catService;
    }

    public String getInf(String action) {
        return catService.getInf(action);
    }


}
