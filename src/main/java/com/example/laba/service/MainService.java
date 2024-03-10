package com.example.laba.service;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MainService {
    private final CatService catService;


    public JsonNode getInf(String action) {
        return catService.getInf(action);
    }

    @SneakyThrows
    public void catInf(String action) {
        catService.saveInfCat(action);
    }


}
