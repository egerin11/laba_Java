package com.example.laba.service.mapper_dto;

import com.example.laba.model.CatFact;
import com.example.laba.model.dto.CatFactDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CatFactMapperDto implements Function<CatFact, CatFactDto> {
    @Override
    public CatFactDto apply(@NotNull CatFact catFact) {
        return new CatFactDto(catFact.getFact(), catFact.getCat());
    }
}
