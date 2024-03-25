package com.example.laba.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {
    private Long id;
    private String name;
    private Integer age;
    @JsonManagedReference
    private List<CatFactDto> facts;
    @JsonManagedReference
    private List<OwnerDto> owners;

}