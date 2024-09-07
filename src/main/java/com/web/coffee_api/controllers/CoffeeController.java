package com.web.coffee_api.controllers;
import com.web.coffee_api.entities.Coffee;
import com.web.coffee_api.services.CoffeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/coffees")
@Tag(name = "Coffees")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve a Coffee by its ID", method = "GET")
    public ResponseEntity<Coffee> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(coffeeService.findById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve a list of all Coffee entities", method = "GET")
    public ResponseEntity<List<Coffee>> getCoffees() {
        return ResponseEntity.ok().body(coffeeService.findAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a Coffee entity and return it", method = "POST")
    public ResponseEntity<Coffee> insert(@RequestBody Coffee coffee) {
        return ResponseEntity.ok().body(coffeeService.insert(coffee));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a Coffee entity by its id", method = "DELETE")
    public void removeById(@PathVariable Long id) {
        coffeeService.deleteById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a Coffee entity by its id and return it", method = "PUT", description = "this method cannot update the entity id")
    public ResponseEntity<Coffee> updateById(@PathVariable Long id, @RequestBody Coffee new_coffee) {
        return ResponseEntity.ok().body(coffeeService.updateById(id, new_coffee));
    }
}
