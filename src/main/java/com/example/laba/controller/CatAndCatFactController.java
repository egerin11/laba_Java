package com.example.laba.controller;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.service.CatService;
import com.example.laba.service.FactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class CatAndCatFactController {

    private final FactService factService;
    private final CatService catService;


    @PostMapping("/add-fact/{id}")
    public CatFact addFact(@RequestBody CatFact catFact,@PathVariable Long id) {
        return factService.addFact(catFact,id);
    }


    @PostMapping("/add-cat")
    public Cat addToMyList(@RequestBody Cat cat) {
        return catService.addCat(cat);
    }
@PostMapping("/fact/{factId}/cat/{catId}")
public Cat addFactToCat(@PathVariable Long factId,@PathVariable Long catId){
        return catService.addFactToCat(factId,catId);
}

    @GetMapping("/cats")
    public List<Cat> getCats() {
        return catService.getAllCat();
    }

    @GetMapping("/cat/{id}")
    public Cat getCatByID(@PathVariable Long id) {
        return catService.getCat(id);
    }

    @GetMapping("/fact/{id}")
    public CatFact getFact(@PathVariable Long id) {
        return factService.getFact(id);
    }


    @GetMapping("/get-all-fact")
    public List<CatFact> getFacts() {
        return factService.getFacts();
    }

    @PutMapping("/update-fact/{id}")
    public CatFact updateFact(@PathVariable Long id, @RequestBody CatFact catFact) {
        return factService.updateFact(id, catFact);
    }

    @PutMapping("/update-cat/{id}")
    public Cat updateCat(@PathVariable Long id, @RequestBody Cat cat) {
        return catService.updateCat(id, cat);
    }

    @DeleteMapping("/delete-fact/{id}")
    public CatFact remove(@PathVariable Long id) {
        return factService.removeFact(id);
    }

    @DeleteMapping("/delete-cat/{id}")
    public String deleteCat(@PathVariable Long id) {
        return catService.removeCat(id);
    }



}

