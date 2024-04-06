package com.example.laba.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** The type Cat dto. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatDto {
  private Long id;
  private String name;
  private Integer age;

  public CatDto(String name) {
    this.name = name;
  }

  @JsonManagedReference private List<CatFactDto> facts;
  @JsonManagedReference private List<OwnerDto> owners;
}
