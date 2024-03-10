package com.example.laba.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "list_facts")
public class MyListFact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "myListFact", cascade = CascadeType.PERSIST)
    private List<CatFact> catFacts = new ArrayList<>();
}