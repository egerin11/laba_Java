package com.example.laba.service.interfaces;

import com.example.laba.model.Cat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface CatInterface {
    JsonNode getInf(String action);

    void saveInfCat(String action) throws JsonProcessingException;

    Cat getCat(Long id);

    Cat addCat(Cat cat);

    String removeCat(Long id);

    Cat updateCat(Long id, Cat cat);

    List<Cat> getAllCat();

    Cat addFactToCat(Long factId, Long catId);

}


