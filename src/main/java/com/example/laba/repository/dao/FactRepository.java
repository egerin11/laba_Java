package com.example.laba.repository.dao;

import com.example.laba.model.CatFact;
import org.springframework.data.repository.CrudRepository;

public interface FactRepository extends CrudRepository<CatFact,Long> {
}
