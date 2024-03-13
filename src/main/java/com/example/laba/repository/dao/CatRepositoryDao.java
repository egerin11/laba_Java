package com.example.laba.repository.dao;

import com.example.laba.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepositoryDao extends JpaRepository<Cat,Long> {

}
