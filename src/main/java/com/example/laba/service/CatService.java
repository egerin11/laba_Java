package com.example.laba.service;

import com.example.laba.model.Breed;
import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.model.Owner;
import com.example.laba.repository.CatInfRepository;
import com.example.laba.repository.CatRepository;
import com.example.laba.repository.dao.CatFactRepository;
import com.example.laba.repository.dao.CatRepositoryDao;
import com.example.laba.repository.dao.OwnerRepository;
import com.example.laba.service.interfaces.CatInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.StreamSupport;


@Service

public class CatService implements CatInterface {

    private static String catInfApiUrl = "https://catfact.ninja/{action}";

    private final RestTemplate restTemplate;
    private final CatRepository catRepository;
    private final CatInfRepository catInfRepository;
    private final CatRepositoryDao catINterface;
    private final CatFactRepository catFactRepository;
    private final FactService factService;
    private final OwnerRepository ownerRepository;

    @Autowired
    public CatService(RestTemplate restTemplate, CatRepository catRepository, CatInfRepository catInfRepository, CatRepositoryDao catINterface, CatFactRepository catFactRepository, FactService factService, OwnerRepository ownerRepository) {
        this.restTemplate = restTemplate;
        this.catRepository = catRepository;
        this.catInfRepository = catInfRepository;
        this.catINterface = catINterface;
        this.catFactRepository = catFactRepository;
        this.factService = factService;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public JsonNode getInf(String action) {
        String url = catInfApiUrl.replace("{action}", action);
        return restTemplate.getForObject(url, JsonNode.class);
    }


    @Override
    public void saveInfCat(String action) throws JsonProcessingException {
        JsonNode json = getInf(action);
        JsonNode dataArray = json.get("data");
        if (action.equals("fact")) {
            String fact = json.get("fact").asText();
            CatFact catFact = new CatFact(fact);
            catInfRepository.saveCatFact(catFact);
            return;
        }
        for (JsonNode catNode : dataArray) {
            if (action.equals("breeds")) {
                String breed = catNode.get("breed").asText();
                String country = catNode.get("country").asText();
                String origin = catNode.get("origin").asText();
                String coat = catNode.get("coat").asText();
                String pattern = catNode.get("pattern").asText();
                Breed cat = new Breed(breed, country, origin, coat, pattern);
                catRepository.saveCat(cat);
            } else if (action.equals("facts")) {
                String fact = catNode.get("fact").asText();
                CatFact catFact = new CatFact(fact);
                catInfRepository.saveCatFact(catFact);
            }
        }
    }

    @Override
    public Cat addCat(@NotNull Cat cat) {
        Cat newCat = new Cat();
        newCat.setName(cat.getName());
        newCat.setAge(cat.getAge());

        Cat savedCat = catINterface.save(newCat);

        for (CatFact fact : cat.getFacts()) {
            fact.setCat(savedCat);
            catFactRepository.save(fact);
        }
        for (Owner owner : cat.getOwners()) {
            Owner existingOwner = ownerRepository.findByName(owner.getName());
            if (existingOwner == null) {
                existingOwner = ownerRepository.save(owner);
            }
            savedCat.addOwner(existingOwner);
            catINterface.save(savedCat);
            ownerRepository.save(existingOwner);
        }

        return cat;
    }


    @Override
    public Cat getCat(Long id) {
        return catINterface.findById(id).orElse(null);
    }

    @Override
    public List<Cat> getAllCat() {
        return StreamSupport.stream(catINterface.findAll().spliterator(), false).toList();
    }

    @Override
    public String removeCat(Long id) {
        Cat cat = getCat(id);
        catINterface.delete(cat);

        return "delete";
    }

    @Override
    public Cat updateCat(Long id, @NotNull Cat cat) {
        Cat existingCat = getCat(id);

        existingCat.setName(cat.getName());
        existingCat.setAge(cat.getAge());

        if (cat.getOwners() != null) {
            for (Owner owner : cat.getOwners()) {
                if (!existingCat.getOwners().contains(owner)) {
                    existingCat.addOwner(owner);
                    ownerRepository.save(owner);
                    catINterface.save(existingCat);
                }
            }
        }

        if (cat.getFacts() != null) {
            for (CatFact fact : cat.getFacts()) {
                if (!existingCat.getFacts().contains(fact)) {
                    fact.setCat(existingCat);
                    fact.setFact(fact.getFact());
                    catFactRepository.save(fact);

                }
            }
        }
        return catINterface.save(existingCat);
    }

    @Override
    public Cat addFactToCat(Long factId, Long catId) {
        Cat cat = getCat(catId);
        CatFact fact = factService.getFact(factId);
        if (!cat.getFacts().contains(fact)) {
            cat.getFacts().add(fact);
            fact.setCat(cat);
            catINterface.save(cat);
            catFactRepository.save(fact);
        }
        return cat;
    }


}
