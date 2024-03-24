package com.example.laba.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatDTO {
    private Long id;
    private String name;
    private Integer age;
    private List<CatFactDTO> facts;
    private List<OwnerDTO> owners;

}