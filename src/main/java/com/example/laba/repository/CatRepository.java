package com.example.laba.repository;

import com.example.laba.model.Cat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CatRepository {
    private List<Cat> cats = new ArrayList<>();

    public Cat saveCat(Cat cat) {
        cats.add(cat);
        return cat;
    }

    public List<Cat> getCats() {
        return cats;
    }

}
