package com.example.laba.repository;

import com.example.laba.model.CatFact;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CatInfRepository{
    private List<CatFact> catFacts = new ArrayList<>();

    public CatFact saveCatFact(CatFact catFact) {
        catFacts.add(catFact);
        return catFact;
    }
    public List<CatFact> getFacts() {
        return catFacts;
    }


    }

