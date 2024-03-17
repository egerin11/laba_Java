package com.example.laba.service;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.repository.dao.CatFactRepository;
import com.example.laba.repository.dao.CatRepositoryDao;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class FactService {

    private final CatFactRepository listFactRepository;
private final CatRepositoryDao catRepositoryDao;
    @Autowired
    public FactService(CatFactRepository listFactRepository, CatRepositoryDao catRepositoryDao) {
        this.listFactRepository = listFactRepository;
        this.catRepositoryDao = catRepositoryDao;
    }

    public CatFact addFact(@NotNull CatFact catFact, Long id) {
        Cat cat = catRepositoryDao.findById(id).orElse(null);
        catFact.setCat(cat);
        return listFactRepository.save(catFact);
    }


    public List<CatFact> getFacts() {
        return StreamSupport.stream(listFactRepository.findAll().spliterator(), false).toList();
    }

    public CatFact getFact(Long id) {
        return listFactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cat not found with id :" + id));
    }

    public CatFact removeFact(Long id) {
        CatFact catFact = getFact(id);
        listFactRepository.delete(catFact);
        return catFact;
    }

    public CatFact updateFact(Long id, @NotNull CatFact catFact) {
        CatFact oldItem = getFact(id);
        oldItem.setFact(catFact.getFact());
        listFactRepository.save(oldItem);
        return oldItem;
    }

}
