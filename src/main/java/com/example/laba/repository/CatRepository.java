package com.example.laba.repository;

import com.example.laba.model.Breed;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CatRepository {
    private List<Breed> breeds = new ArrayList<>();

    public Breed saveCat(Breed breed) {
        breeds.add(breed);
        return breed;
    }

    public List<Breed> getCats() {
        return breeds;
    }

}
