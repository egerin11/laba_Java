package com.example.laba.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "breed")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String breeds;
    private String country;
    private String origin;
    private String coat;
    private String pattern;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "breed_table",
            joinColumns = {@JoinColumn(name = "breed_id")},
            inverseJoinColumns = {@JoinColumn(name = "cat_id")}
    )
    private List<Cat> cats = new ArrayList<>();

    @Override
    public String toString() {
        return breeds + country + origin + coat + pattern;
    }

    public Breed() {
    }

    public void addCat(Cat cat) {
        cats.add(cat);
        cat.getBreeds().add(this);
    }

    public void removeCat(Cat cat) {
        cats.remove(cat);
        cat.getBreeds().remove(this);
    }

    public Breed(String breed, String country, String origin, String coat, String pattern) {
        this.breeds = breed;
        this.country = country;
        this.origin = origin;
        this.coat = coat;
        this.pattern = pattern;
    }
}
