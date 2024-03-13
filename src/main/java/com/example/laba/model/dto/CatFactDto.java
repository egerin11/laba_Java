package com.example.laba.model.dto;

import com.example.laba.model.CatFact;
import lombok.Data;

@Data
public class CatFactDto {

    private Long id;
    private String facts;
    private int length;

    public static CatFactDto from(CatFact catFact) {
        CatFactDto catFactDto = new CatFactDto();
        catFactDto.setId(catFact.getId());
        catFactDto.setFacts(catFact.getFacts());
        catFactDto.setLength(catFact.getLength());
        return catFactDto;
    }


}

