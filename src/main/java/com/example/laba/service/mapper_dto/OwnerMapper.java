package com.example.laba.service.mapper_dto;

import com.example.laba.model.Owner;
import com.example.laba.model.dto.OwnerDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OwnerMapperDto implements Function<Owner, OwnerDto> {
    @Override
    public OwnerDto apply(@NotNull Owner owner) {
        return new OwnerDto(owner.getName(), owner.getCats());
    }
}
