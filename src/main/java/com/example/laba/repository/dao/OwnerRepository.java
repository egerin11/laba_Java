package com.example.laba.repository.dao;

import com.example.laba.model.Owner;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Hidden
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByName(String name);
}
