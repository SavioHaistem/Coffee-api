package com.web.coffee_api.controllers;

import com.web.coffee_api.entities.CoffeeCup;
import com.web.coffee_api.services.CoffeeCupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/coffees_cups")
@Tag(name = "coffee-api")
public class CoffeeCupController {
    @Autowired
    private CoffeeCupService coffeeCupService;

    @GetMapping
    public ResponseEntity<List<CoffeeCup>> findAll() {
        return ResponseEntity.ok().body(coffeeCupService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CoffeeCup> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(coffeeCupService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        coffeeCupService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<CoffeeCup> insert(@RequestBody CoffeeCup coffeeCup) {
        return ResponseEntity.ok().body(coffeeCupService.insert(coffeeCup));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoffeeCup> updateById(@PathVariable Long id, @RequestBody CoffeeCup new_coffeeCup) {
        return ResponseEntity.ok().body(coffeeCupService.updateById(id,new_coffeeCup));
    }
}
