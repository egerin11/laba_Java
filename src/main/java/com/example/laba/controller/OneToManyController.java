package com.example.laba.controller;

import com.example.laba.model.CatFact;
import com.example.laba.model.MyListFact;
import com.example.laba.repository.dao.FactRepository;
import com.example.laba.repository.dao.MyListFactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class OneToManyController {

    @Autowired
    private final FactRepository listFactRepository;
    @Autowired
    private final MyListFactRepository myListFactRepository;

    @PostMapping("/add-fact")
    public void addFact(@RequestBody CatFact fact) {
        listFactRepository.save(fact);
    }

    @PostMapping("/add-to-list")
    public void addFact(@RequestBody MyListFact myListFact) {
        myListFactRepository.save(myListFact);
    }

    @GetMapping("/list/{id}")
    public CatFact getFact(@PathVariable Long id) {
        return listFactRepository.findById(id).orElseThrow();
    }

    @GetMapping("/all-list/{id}")
    public MyListFact getListFact(@PathVariable Long id) {
        return myListFactRepository.findById(id).orElseThrow();
    }

}
