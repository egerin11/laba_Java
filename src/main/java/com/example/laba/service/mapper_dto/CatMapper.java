package com.example.laba.service.mapper_dto;

import com.example.laba.model.Cat;
import com.example.laba.model.dto.CatDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CatMapperDto implements Function<Cat, CatDto> {
    @Override
    public CatDto apply(@NotNull Cat cat) {
        return new CatDto(cat.getName(), cat.getAge(),cat.getFacts(), cat.getOwners());
    }
}
