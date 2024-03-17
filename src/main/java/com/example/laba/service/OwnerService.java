package com.example.laba.service;

import com.example.laba.model.Cat;
import com.example.laba.model.Owner;
import com.example.laba.repository.dao.CatRepositoryDao;
import com.example.laba.repository.dao.OwnerRepository;
import com.example.laba.service.interfaces.OwnerInterface;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

@Service
public class OwnerService implements OwnerInterface {
    private final OwnerRepository ownerRepository;
    private final CatRepositoryDao catRepositoryDao;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, CatRepositoryDao catRepositoryDao) {
        this.ownerRepository = ownerRepository;
        this.catRepositoryDao = catRepositoryDao;
    }

    @Override
    public List<Owner> getAllOwners() {
        return StreamSupport.stream(ownerRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Owner addOwner(@NotNull Owner owner) {
        if (owner.getCats() != null) {
            Set<Cat> savedCats = new HashSet<>();
            for (Cat cat : owner.getCats()) {
                if (cat.getId() == null) {
                    cat = catRepositoryDao.save(cat);
                }
                if (!cat.getOwners().contains(owner)) {
                    cat.addOwner(owner);
                    savedCats.add(cat);
                }
            }
            owner.setCats(savedCats);
        }
        return ownerRepository.save(owner);
    }

    @Override
    public Owner getOwner(Long id) {
        return ownerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("owner not found"));
    }

    @Override
    public Owner removeOwner(Long id) {
        Owner owner = getOwner(id);
        ownerRepository.delete(owner);
        return owner;
    }

    @Override
    public Owner updateOwner(Long id, @NotNull Owner owner) {
        Owner existingOwner = getOwner(id);
        existingOwner.setName(owner.getName());
        return ownerRepository.save(existingOwner);

    }

    @Override
    public Owner deleteCatToOwner(Long catId, Long ownerId) {
        Cat cat = catRepositoryDao.findById(catId).orElse(null);
        Owner owner = getOwner(ownerId);
        if (cat != null) {
            cat.removeOwner(owner);
        }
        ownerRepository.save(owner);
        return owner;
    }
}