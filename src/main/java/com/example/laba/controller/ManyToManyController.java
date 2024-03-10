package com.example.laba.controller;

import com.example.laba.model.Breed;
import com.example.laba.model.Cat;
import com.example.laba.service.ManyToManyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/db")
@RequiredArgsConstructor

public class ManyToManyController {
    private final ManyToManyService manyToManyService;

    @PostMapping("/add-breed")
    public String postData(@RequestBody Breed breed) {
        manyToManyService.postBreed(breed);
        return "savedt:;";
    }

    @PostMapping("/add-cat")
    public String postData(@RequestBody Cat cat) {
        manyToManyService.postData(cat);
        return "savedt:;";
    }

    @GetMapping("/breeds")
    public Iterable<Breed> get() {
        return manyToManyService.get();
    }

    @GetMapping("/cats")
    public Iterable<Cat> getAllCats() {
        return manyToManyService.getCat();
    }

    @GetMapping("/breeds/{id}")
    public Breed getBreedById(@PathVariable Long id) {
        return manyToManyService.getById(id);
    }

    @GetMapping("/cats/{id}")
    public Cat getCatById(@PathVariable Long id) {
        return manyToManyService.getCatById(id);
    }

    @PostMapping("/breeds/{breedId}/cats/{catId}")
    public void addCatToBreed(@PathVariable Long breedId, @PathVariable Long catId) {
        manyToManyService.addCatToBreed(breedId, catId);
    }

    @DeleteMapping("/breeds/{breedId}/cats/{catId}")
    public void deleteCatToBreed(@PathVariable Long breedId, @PathVariable Long catId) {
        manyToManyService.deleteCatToBreed(breedId, catId);
    }

    @DeleteMapping("/delete/breed/{id}")
    public void deleteBreed(@PathVariable Long id) {
        manyToManyService.deleteBreed(id);
    }
    @DeleteMapping("/delete/cat/{id}")
    public void deleteCat(@PathVariable Long id) {
        manyToManyService.deleteCat(id);
    }
    @PutMapping("/update/cat/{id}")
    public void updateCat(@PathVariable Long id,@RequestBody Cat cat){
        manyToManyService.updateCat(id,cat);
    }
    @PutMapping("/update/breed/{id}")
    public void updateBreed(@PathVariable Long id,@RequestBody Breed breed){
        manyToManyService.updateBreed(id,breed);
    }
}
