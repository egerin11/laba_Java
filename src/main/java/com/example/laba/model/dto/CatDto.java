package com.example.laba.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CatRequest {
    private String name;
    private Integer age;
    private Set<CatFactRequest> facts;
}
