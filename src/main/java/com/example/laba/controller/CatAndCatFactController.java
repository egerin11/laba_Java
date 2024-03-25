package com.example.laba.controller;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.model.dto.CatDto;
import com.example.laba.service.CatService;
import com.example.laba.service.FactService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class CatAndCatFactController {

    private final FactService factService;
    private final CatService catService;


    public CatAndCatFactController(FactService factService, CatService catService) {
        this.factService = factService;
        this.catService = catService;

    }

    @PostMapping("/add-fact/{id}")
    @ApiOperation("add fact about cat")
    public CatFact addFact(@RequestBody CatFact catFact, @PathVariable Long id) {
        return factService.addFact(catFact, id);
    }


    @PostMapping("/add-cat")
    public CatDto addToMyList(@RequestBody Cat cat) {
        return catService.addCat(cat);
    }

    @PostMapping("/fact/{factId}/cat/{catId}")
    public Cat addFactToCat(@PathVariable Long factId, @PathVariable Long catId) {
        return catService.addFactToCat(factId, catId);
    }

    @GetMapping("/find-cat-by-owner/{id}")
    public List<CatDto> findCatsByOwnerId(@PathVariable Long id) {
        return catService.findCatsByOwnerId(id);
    }

    @GetMapping("/cats")
    public List<CatDto> getCats() {
        return catService.getAllCat();
    }

    @GetMapping("/cat/{id}")
    public CatDto getCatByID(@PathVariable Long id) {
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
    public CatDto updateCat(@PathVariable Long id, @RequestBody Cat cat) {
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

