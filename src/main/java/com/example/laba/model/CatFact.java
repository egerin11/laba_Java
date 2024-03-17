package com.example.laba.model;

import com.example.laba.model.dto.CatFactDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "fact")
public class CatFact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fact")
    private String facts;

    @Column(name = "length")
    private int length;


    public CatFact(String facts, int length) {
        this.facts = facts;
        this.length = length;
    }

    public CatFact() {

    }

    public static CatFact from(CatFactDto catFactDto) {
        CatFact catFact = new CatFact();
        catFact.setFacts(catFactDto.getFacts());
        catFact.setLength(catFactDto.getLength());
        return catFact;
    }
}

