package com.example.laba.repository.dao;

import com.example.laba.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Owner findByName(String name);
}
