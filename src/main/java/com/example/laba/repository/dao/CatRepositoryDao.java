package com.example.laba.repository.dao;

import com.example.laba.model.Cat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepositoryDao extends JpaRepository<Cat, Long> {
    @Transactional
    @Query("SELECT c FROM Cat c JOIN c.owners o WHERE o.id = :id")
    List<Cat> findCatsByOwnerId(@Param("id") Long id);


}
