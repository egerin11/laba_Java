package com.example.laba.service.interfaces;

import com.example.laba.model.Cat;
import com.example.laba.model.dto.CatDto;

import java.util.List;

public interface CatInterface {


    CatDto getCat(Long id);

    CatDto addCat(Cat cat);

    String removeCat(Long id);

    CatDto updateCat(Long id, Cat cat);

    List<CatDto> getAllCat();

    Cat addFactToCat(Long factId, Long catId);
    List<CatDto> findCatsByOwnerId(Long id);
}


