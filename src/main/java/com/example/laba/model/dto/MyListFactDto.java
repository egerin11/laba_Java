package com.example.laba.model.dto;


import com.example.laba.model.CatFact;
import com.example.laba.model.MyListFact;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MyListFactDto {

    private Long id;
    private List<CatFact> catFacts = new ArrayList<>();

    public static MyListFactDto from(MyListFact myListFact) {
        MyListFactDto myListFactDto = new MyListFactDto();
        myListFactDto.setId(myListFact.getId());
        myListFactDto.setCatFacts(myListFact.getCatFacts());
        return myListFactDto;
    }

}
