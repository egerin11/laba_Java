package com.example.laba.service;

import com.example.laba.model.CatFact;
import com.example.laba.repository.dao.FactRepository;
import com.example.laba.repository.dao.MyListFactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OneToManyService {
    @Autowired
    private final FactRepository listFactRepository;
    @Autowired
    private final MyListFactRepository myListFactRepository;

    public CatFact addFact(CatFact catFact) {
        return listFactRepository.save(catFact);
    }

    public List<CatFact> getFacts() {
        return StreamSupport.
                stream(listFactRepository.findAll().spliterator(), false).toList();
    }
    public CatFact getFact(Long id){
        return  listFactRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cat not found with id :" + id));
    }
    public CatFact removeFact(Long id){
        CatFact catFact=getFact(id);
        listFactRepository.delete(catFact);
        return catFact;
    }
    public  CatFact updateFact(Long id,CatFact catFact){
        CatFact oldItem=getFact(id);
        oldItem.setFacts(catFact.getFacts());
        return oldItem;
    }
}
