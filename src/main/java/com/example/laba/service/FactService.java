package com.example.laba.service;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.repository.dao.CatFactRepository;
import com.example.laba.repository.dao.CatRepositoryDao;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FactService {
    private final CatFactRepository listCatFactRepository;
    private final CatRepositoryDao catRepositoryDao;

    public CatFact addFact(CatFact catFact, Long id) {
        Cat cat = catRepositoryDao.findById(id).orElse(null);
        if(cat!=null) {
            catFact.setCat(cat);
        }
        return listCatFactRepository.save(catFact);
    }


    public List<CatFact> getFacts() {
        return StreamSupport.stream(listCatFactRepository.findAll().spliterator(), false).toList();
    }

    public CatFact getFact(Long id) {
        return listCatFactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cat not found with id :" + id));
    }

    public CatFact removeFact(Long id) {
        CatFact catFact = getFact(id);
        listCatFactRepository.delete(catFact);
        return catFact;
    }

    public CatFact updateFact(Long id, @NotNull CatFact catFact) {
        CatFact oldItem = getFact(id);
        oldItem.setFact(catFact.getFact());

        return listCatFactRepository.save(oldItem);
    }

}
