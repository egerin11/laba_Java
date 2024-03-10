package com.example.laba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "list_facts_id")
    private MyListFact myListFact;

    public CatFact() {
    }

    public CatFact(String facts, int length) {
        this.facts = facts;
        this.length = length;
    }
}

