package com.web.coffee_api.controllers;

import com.web.coffee_api.entities.CoffeeCup;
import com.web.coffee_api.services.CoffeeCupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/coffees_cups")
@Tag(name = "Coffee-Cups")
public class CoffeeCupController {
    @Autowired
    private CoffeeCupService coffeeCupService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieves a Coffee-Cup entity list", method = "GET")
    public ResponseEntity<List<CoffeeCup>> findAll() {
        return ResponseEntity.ok().body(coffeeCupService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieves a Coffee-Cup entity by its id",method = "GET")
    public ResponseEntity<CoffeeCup> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(coffeeCupService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a Coffee-Cup by its id",method = "DELETE")
    public void deleteById(@PathVariable Long id) {
        coffeeCupService.deleteById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a Coffee-Cup entity and return it", method = "POST")
    public ResponseEntity<CoffeeCup> insert(@RequestBody CoffeeCup coffeeCup) {
        return ResponseEntity.ok().body(coffeeCupService.insert(coffeeCup));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a Coffee-Cup by its id and return it", method = "PUT", description = "this method cannot update the entity id")
    public ResponseEntity<CoffeeCup> updateById(@PathVariable Long id, @RequestBody CoffeeCup new_coffeeCup) {
        return ResponseEntity.ok().body(coffeeCupService.updateById(id,new_coffeeCup));
    }
}
