package com.example.laba.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name="cat")
public class Cat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String catName;
    @JsonIgnore
    @ManyToMany(mappedBy = "cats")
    private List<Breed> breeds=new ArrayList<>();

}
