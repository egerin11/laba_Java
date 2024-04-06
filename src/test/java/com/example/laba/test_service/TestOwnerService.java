package com.example.laba.test_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.laba.model.Owner;
import com.example.laba.model.dto.OwnerDto;
import com.example.laba.repository.dao.OwnerRepository;
import com.example.laba.service.OwnerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestOwnerService {

  @Mock private OwnerRepository ownerRepository;
  @Mock private ModelMapper mapper;
  @InjectMocks private OwnerService ownerService;

  @Test
  void testGetAllOwners() {
    List<Owner> owners = new ArrayList<>();
    Owner owner1 = new Owner();
    owner1.setId(1L);
    owner1.setName("John Doe");
    Owner owner2 = new Owner();
    owner2.setId(2L);
    owner2.setName("Jane Smith");

    owners.add(owner1);
    owners.add(owner2);

    List<OwnerDto> ownerDtos = new ArrayList<>();
    OwnerDto ownerDto1 = new OwnerDto(1L, "John Doe");
    OwnerDto ownerDto2 = new OwnerDto(2L, "Jane Smith");
    ownerDtos.add(ownerDto1);
    ownerDtos.add(ownerDto2);

    when(ownerRepository.findAll()).thenReturn(owners);
    when(mapper.map(owner1, OwnerDto.class)).thenReturn(ownerDto1);
    when(mapper.map(owner2, OwnerDto.class)).thenReturn(ownerDto2);

    List<OwnerDto> result = ownerService.getAllOwners();

    assertEquals(ownerDtos, result);
  }

  @Test
  void testGetOwner() {
    Long ownerId = 1L;

    Owner owner1 = new Owner();
    owner1.setId(1L);
    owner1.setName("John Doe");
    when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner1));

    Owner result = ownerService.getOwner(ownerId);

    assertEquals(owner1, result);
  }

  @Test
  void testGetOwnerNotFound() {
    Long ownerId = 1L;
    when(ownerRepository.findById(ownerId)).thenReturn(Optional.empty());
    assertThrows(ResourceNotFoundException.class, () -> ownerService.getOwner(ownerId));
  }

  @Test
  void testRemoveOwner() {
    Long ownerId = 1L;
    Owner owner1 = new Owner();
    owner1.setId(1L);
    owner1.setName("John Doe");
    when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner1));

    Owner result = ownerService.removeOwner(ownerId);

    assertEquals(owner1, result);
    verify(ownerRepository, times(1)).delete(owner1);
  }

  @Test
  void testUpdateOwner() {

    Long ownerId = 1L;
    Owner existingOwner = new Owner();
    existingOwner.setId(1L);
    existingOwner.setName("John Doe");
    Owner updatedOwner = new Owner();
    updatedOwner.setId(ownerId);
    updatedOwner.setName("Zhenya");
    when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(existingOwner));
    when(ownerRepository.save(any(Owner.class))).thenReturn(updatedOwner);

    Owner result = ownerService.updateOwner(ownerId, updatedOwner);

    assertEquals(updatedOwner, result);
    verify(ownerRepository, times(1)).save(existingOwner);
  }
}
