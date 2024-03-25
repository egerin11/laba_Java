package com.example.laba.controller;


import com.example.laba.model.Owner;
import com.example.laba.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
@RequiredArgsConstructor
public class OwnerAndCatController {

    private final OwnerService ownerService;

    @GetMapping("/owner/{id}")
    public Owner getOwner(@PathVariable Long id) {
        return ownerService.getOwner(id);
    }

    @GetMapping("/owners")
    public List<Owner> getOwners() {
        return ownerService.getAllOwners();
    }

    @PostMapping("/add-owner")
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerService.addOwner(owner);
    }

    @PutMapping("/update-owner/{id}")
    public Owner updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        return ownerService.updateOwner(id, owner);
    }

    @DeleteMapping("/cat/{catId}/owner/{ownerId}")
    public Owner deleteCatToOwner(@PathVariable Long catId, @PathVariable Long ownerId) {
        return ownerService.deleteCatToOwner(catId, ownerId);
    }

    @DeleteMapping("/delete-owner/{id}")
    public String deleteOwner(@PathVariable Long id) {
        return "delete";
    }
}
