package com.example.laba.repository.dao;

import com.example.laba.model.CatFact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatFactRepository extends JpaRepository<CatFact,Long> {
CatFact findByFact(String fact);
}
