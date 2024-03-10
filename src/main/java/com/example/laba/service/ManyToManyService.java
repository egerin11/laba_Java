package com.example.laba.service;

import com.example.laba.model.Breed;
import com.example.laba.model.Cat;
import com.example.laba.repository.DAO.BreedInterface;
import com.example.laba.repository.DAO.CatINterface;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManyToManyService {

    @Autowired
    private final BreedInterface breedInterface;
    @Autowired
    private final CatINterface catINterface;

    public void postBreed(Breed breed) {
        breedInterface.save(breed);
    }

    public void postData(Cat cat) {
        catINterface.save(cat);
    }

    public Iterable<Breed> get() {
        assert breedInterface != null;
        return breedInterface.findAll();
    }

    public Iterable<Cat> getCat() {
        return catINterface.findAll();
    }

    public Breed getById(Long id) {
        return   breedInterface.findById(id).orElseThrow(() -> new ResourceNotFoundException("breed not found with id :" + id));

    }

    public Cat getCatById(Long id) {
        return catINterface.findById(id).orElseThrow(() -> new ResourceNotFoundException("cat not found with id :" + id));
    }

    public void addCatToBreed(Long breedId, Long catId) {
        Breed breed = breedInterface.findById(breedId).orElseThrow(() -> new ResourceNotFoundException("Breed not found with id :" + breedId));
        Cat cat = catINterface.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Cat not found with id :" + catId));
        breed.addCat(cat);
        breedInterface.save(breed);
    }

    public void deleteCatToBreed(Long breedId, Long catId) {
        Breed breed = breedInterface.findById(breedId).orElseThrow(() -> new ResourceNotFoundException("Breed not found with id :" + breedId));
        Cat cat = catINterface.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Cat not found with id :" + catId));
        breed.removeCat(cat);
        breedInterface.save(breed);

    }

    public void deleteBreed(Long id) {
        breedInterface.delete(breedInterface.findById(id).orElseThrow());
    }

    public void deleteCat(Long id) {
        catINterface.delete(catINterface.findById(id).orElseThrow());

    }

    public void updateCat(Long id, Cat cat) {
        Cat oldCat = catINterface.findById(id).orElseThrow();
        cat.setId(oldCat.getId());
        catINterface.save(cat);
    }

    public void updateBreed(Long id, Breed breed) {
        Breed oldBreed = breedInterface.findById(id).orElseThrow();
        breed.setId(oldBreed.getId());
        breedInterface.save(breed);
    }
}
