package com.example.laba.service.interfaces;

import com.example.laba.model.Owner;

import java.util.List;

public interface OwnerInterface {

    Owner getOwner(Long id);

    Owner addOwner(Owner owner);

    List<Owner> getAllOwners();

    Owner removeOwner(Long id);

    Owner updateOwner(Long id, Owner owner);

    Owner deleteCatToOwner(Long catId, Long ownerId);

}
