package com.example.laba.service.interfaces;

import com.example.laba.model.Cat;
import com.example.laba.model.dto.CatDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface CatInterface {
    JsonNode getInf(String action);

    void saveInfCat(String action) throws JsonProcessingException;

    CatDto getCat(Long id);

    CatDto addCat(Cat cat);

    String removeCat(Long id);

    CatDto updateCat(Long id, Cat cat);

    List<CatDto> getAllCat();

    Cat addFactToCat(Long factId, Long catId);
    List<CatDto> findCatsByOwnerId(Long id);
}


