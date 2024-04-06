package com.example.laba.test_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.laba.model.Cat;
import com.example.laba.model.dto.CatDto;
import com.example.laba.repository.dao.CatRepositoryDao;
import com.example.laba.service.CatService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class TestCatService {
  @Mock private CatRepositoryDao catRepositoryDao;
  @Mock private ModelMapper mapper;
  @InjectMocks private CatService catService;

  @Test
  void testGetCat() {
    Cat cat = new Cat();
    cat.setAge(5);
    cat.setId(1L);
    cat.setName("jorik");

    CatDto expectedCatDto = new CatDto();
    expectedCatDto.setAge(5);
    expectedCatDto.setId(1L);
    expectedCatDto.setName("jorik");

    when(catRepositoryDao.findById(1L)).thenReturn(Optional.of(cat));
    when(mapper.map(cat, CatDto.class)).thenReturn(expectedCatDto);

    CatDto testCatDto = catService.getCat(1L);

    assertNotNull(testCatDto);
    assertEquals(expectedCatDto, testCatDto);
  }

  @Test
  void testGetAllCat() {
    Cat cat1 = new Cat();
    cat1.setName("Fluffy");
    Cat cat2 = new Cat();
    cat2.setName("Whiskers");
    List<Cat> catList = Arrays.asList(cat1, cat2);

    when(catRepositoryDao.findAll()).thenReturn(catList);
    when(mapper.map(cat1, CatDto.class)).thenReturn(new CatDto("Fluffy"));
    when(mapper.map(cat2, CatDto.class)).thenReturn(new CatDto("Whiskers"));

    List<CatDto> result = catService.getAllCat();
    assertEquals(2, result.size());
    assertEquals("Fluffy", result.get(0).getName());
    assertEquals("Whiskers", result.get(1).getName());
  }

  @Test
  void testRemoveCat() {
    catService.removeCat(1L);
    verify(catRepositoryDao).deleteById(1L);
    assertEquals("delete", catService.removeCat(1L));
  }

  @Test
  void testFindCatsByOwnerId() {
    Cat cat1 = new Cat();
    cat1.setName("Fluffy");
    Cat cat2 = new Cat();
    cat2.setName("Whiskers");
    List<Cat> catList = Arrays.asList(cat1, cat2);

    when(catRepositoryDao.findCatsByOwnerId(1L)).thenReturn(catList);
    when(mapper.map(cat1, CatDto.class)).thenReturn(new CatDto("Fluffy"));
    when(mapper.map(cat2, CatDto.class)).thenReturn(new CatDto("Whiskers"));

    List<CatDto> result = catService.findCatsByOwnerId(1L);

    assertEquals(2, result.size());
    assertEquals("Fluffy", result.get(0).getName());
    assertEquals("Whiskers", result.get(1).getName());
  }
}
