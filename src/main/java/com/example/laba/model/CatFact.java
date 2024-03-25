package com.example.laba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class CatFact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fact;


    @ManyToOne
    @JoinColumn(name = "cat_id")
    @JsonIgnore
    private Cat cat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatFact catFact = (CatFact) o;
        return Objects.equals(fact, catFact.fact);
    }

    @Override
    public String toString() {
        return "CatFact{" +
                "id=" + id +
                ", fact='" + fact + '\'' +
                ", cat=" + cat +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(fact);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public CatFact() {
    }

    public CatFact(String fact) {
        this.fact = fact;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}

