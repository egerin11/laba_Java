package com.example.laba.controller;

import com.example.laba.model.Cat;
import com.example.laba.model.CatFact;
import com.example.laba.model.dto.CatDto;
import com.example.laba.service.CatService;
import com.example.laba.service.FactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
@Tag(name = "Cat and Fact", description = "API to manipulate  cat and fact")
public class CatAndCatFactController {

    private final FactService factService;
    private final CatService catService;


    public CatAndCatFactController(FactService factService, CatService catService) {
        this.factService = factService;
        this.catService = catService;

    }

    @PostMapping("/add-fact/{id}")
    @Operation(summary = "updates fact by id") // 2

    public CatFact addFact(@RequestBody CatFact catFact, @PathVariable Long id) {
        return factService.addFact(catFact, id);
    }


    @PostMapping("/add-cat")
    @Operation(summary = "updates fact by id") // 2

    public CatDto addToMyList(@RequestBody Cat cat) {
        return catService.addCat(cat);
    }

    @PostMapping("/fact/{factId}/cat/{catId}")
    @Operation(summary = "updates fact by id") // 2

    public Cat addFactToCat(@PathVariable Long factId, @PathVariable Long catId) {
        return catService.addFactToCat(factId, catId);
    }

    @GetMapping("/find-cat-by-owner/{id}")
    @Operation(summary = "updates fact by id") // 2

    public List<CatDto> findCatsByOwnerId(@PathVariable Long id) {
        return catService.findCatsByOwnerId(id);
    }

    @GetMapping("/cats")
    @Operation(summary = "updates fact by id") // 2

    public List<CatDto> getCats() {
        return catService.getAllCat();
    }

    @GetMapping("/cat/{id}")
    @Operation(summary = "updates fact by id") // 2

    public CatDto getCatByID(@PathVariable Long id) {
        return catService.getCat(id);
    }

    @GetMapping("/fact/{id}")
    public CatFact getFact(@PathVariable Long id) {
        return factService.getFact(id);
    }


    @GetMapping("/get-all-fact")
    @Operation(summary = "updates fact by id") // 2

    public List<CatFact> getFacts() {
        return factService.getFacts();
    }


    @PutMapping("/update-fact/{id}")
    @Operation(summary = "updates fact by id") // 2
    public CatFact updateFact(@PathVariable Long id, @RequestBody CatFact catFact) {
        return factService.updateFact(id, catFact);
    }

    @PutMapping("/update-cat/{id}")
    @Operation(summary = "updates fact by id") // 2

    public CatDto updateCat(@PathVariable Long id, @RequestBody Cat cat) {
        return catService.updateCat(id, cat);
    }

    @DeleteMapping("/delete-fact/{id}")
    @Operation(summary = "updates fact by id") // 2

    public CatFact remove(@PathVariable Long id) {
        return factService.removeFact(id);
    }

    @DeleteMapping("/delete-cat/{id}")
    @Operation(summary = "updates fact by id") // 2

    public String deleteCat(@PathVariable Long id) {
        return catService.removeCat(id);
    }


}

