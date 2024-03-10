package com.example.laba.repository.DAO;

import com.example.laba.model.Breed;
import org.springframework.data.repository.CrudRepository;


public interface BreedInterface extends CrudRepository<Breed,Long> { // Noncompliant
}
