package com.example.laba.service;

import com.example.laba.cache.LRUCache;
import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.model.Owner;
import com.example.laba.model.dto.CatDto;
import com.example.laba.repository.dao.CatFactRepository;
import com.example.laba.repository.dao.CatRepositoryDao;
import com.example.laba.repository.dao.OwnerRepository;
import com.example.laba.service.interfaces.CatInterface;
import io.swagger.v3.oas.annotations.Hidden;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Hidden
public class CatService implements CatInterface {
    private final Logger logger = LoggerFactory.getLogger(CatService.class);


    private final CatRepositoryDao catInterface;
    private final CatFactRepository catFactRepository;
    private final FactService factService;
    private final OwnerRepository ownerRepository;
    private final ModelMapper mapper;


    private final LRUCache<Long, CatDto> catCache = new LRUCache<>(2);

    @Autowired
    public CatService(CatRepositoryDao catInterface, CatFactRepository catFactRepository, FactService factService, OwnerRepository ownerRepository, ModelMapper mapper) {
        this.catInterface = catInterface;
        this.catFactRepository = catFactRepository;
        this.factService = factService;
        this.ownerRepository = ownerRepository;
        this.mapper = mapper;
    }


    @Override
    public CatDto addCat(@NotNull Cat cat) {
        Cat newCat = new Cat();
        newCat.setName(cat.getName());
        newCat.setAge(cat.getAge());

        Cat savedCat = catInterface.save(newCat);

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
            catInterface.save(savedCat);
            ownerRepository.save(existingOwner);
        }
        logger.info("добавлен кот");
        return mapper.map(cat, CatDto.class);

    }

    @Override
    public CatDto getCat(Long id) {
        CatDto catDto = catCache.get(id);
        if (catDto == null) {
            Cat cat = catInterface.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
            catDto = mapper.map(cat, CatDto.class);
            if (cat != null) {
                catCache.put(id, catDto);
            }
        }
        catCache.displayCache();

        logger.info("получен кот");

        return catDto;
    }

    @Override
    public List<CatDto> getAllCat() {
        catCache.displayCache();
        return StreamSupport.stream(catInterface.findAll().spliterator(), false)
                .map(cat -> mapper.map(cat, CatDto.class)).toList();
    }


    @Override
    public String removeCat(Long id) {

        catInterface.deleteById(id);
        catCache.remove(id);

        return "delete";
    }

    @Override
    public CatDto updateCat(Long id, @NotNull Cat cat) {
        CatDto catDto = getCat(id);
        Cat existingCat = mapper.map(catDto, Cat.class);


        existingCat.setName(cat.getName());
        existingCat.setAge(cat.getAge());

        if (cat.getOwners() != null) {
            for (Owner owner : cat.getOwners()) {
                if (!existingCat.getOwners().contains(owner)) {
                    existingCat.addOwner(owner);
                    ownerRepository.save(owner);
                    catInterface.save(existingCat);
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
        catCache.put(id, catDto);
        return catDto;

    }

    @Override
    public Cat addFactToCat(Long factId, Long catId) {
        CatDto catDto = getCat(catId);
        Cat cat = mapper.map(catDto, Cat.class);
        CatFact fact = factService.getFact(factId);
        if (!cat.getFacts().contains(fact)) {
            cat.getFacts().add(fact);
            fact.setCat(cat);
            catInterface.save(cat);
            catFactRepository.save(fact);
        }
        return cat;
    }

    @Override
    public List<CatDto> findCatsByOwnerId(Long id) {


        return StreamSupport.stream(catInterface.findCatsByOwnerId(id).spliterator(), false)
                .map(cat -> mapper.map(cat, CatDto.class)).toList();
    }
}