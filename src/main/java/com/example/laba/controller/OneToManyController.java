package com.example.laba.controller;

import com.example.laba.model.CatFact;
import com.example.laba.model.MyListFact;
import com.example.laba.service.FactService;
import com.example.laba.service.ListFactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class OneToManyController {

    private final FactService factService;
    private final ListFactsService listFactsService;

    @PostMapping("/fact/{factId}/list/{listId}")
    public void addFactToList(@PathVariable Long factId, @PathVariable Long listId) {
        listFactsService.addFactToList(factId, listId);
    }

    @PostMapping("/add-fact")
    public CatFact addFact(@RequestBody CatFact fact) {
        return factService.addFact(fact);
    }

    @PostMapping("/add-to-list")
    public MyListFact addToMyList(@RequestBody MyListFact myListFact) {
      return   listFactsService.addToList(myListFact);
    }

    @GetMapping("/fact/{id}")
    public CatFact getFact(@PathVariable Long id) {
        return factService.getFact(id);
    }

    @GetMapping("/all-list/{id}")
    public MyListFact getListFact(@PathVariable Long id) {
        return listFactsService.getFact(id);
    }

    @GetMapping("/get-all-list")
    public List<MyListFact> getListFacts() {
        return listFactsService.getFactsFromList();
    }

    @GetMapping("/get-all-fact")
    public List<CatFact> getFacts() {
        return factService.getFacts();
    }

    @PutMapping("/update-fact/{id}")
    public CatFact updateFact(@PathVariable Long id, @RequestBody CatFact catFact) {
        return factService.updateFact(id, catFact);
    }

    @DeleteMapping("/delete-fact/{id}")
    public CatFact remove(@PathVariable Long id) {
        return factService.removeFact(id);
    }

    @DeleteMapping("/fact/{factId}/list/{listId}")
    public MyListFact deleteFactToList(@PathVariable Long factId, @PathVariable Long listId) {
        return listFactsService.removeFactToList(factId, listId);
    }
    @DeleteMapping("/delete-list/{id}")
    public MyListFact deleteList(@PathVariable Long id){
        return  listFactsService.removeList(id);
    }
}

