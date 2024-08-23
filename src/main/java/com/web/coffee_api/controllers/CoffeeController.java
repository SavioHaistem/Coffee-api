package com.web.coffee_api.controllers;
import com.web.coffee_api.entities.Coffee;
import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.services.CoffeeService;
import com.web.coffee_api.services.CupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/coffees")
@Tag(name = "coffee-api")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search for a coffee that has the given id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "coffee found with success"),
            @ApiResponse(responseCode = "404", description = "coffee not found"),
            @ApiResponse(responseCode = "500", description = "untreated exception")
    })
    public ResponseEntity<Coffee> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(coffeeService.findById(id));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "returns a list of all the coffees in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "operation success return a list of all coffees"),
            @ApiResponse(responseCode = "500",description = "untreated exception")
    })
    public ResponseEntity<List<Coffee>> getCoffees() {
        return ResponseEntity.ok().body(coffeeService.findAll());
    }

    @PostMapping
    @Operation(summary = "insert one coffee on coffees database", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "created coffee operation success"),
            //TODO: handle error of not inserted objects
    })
    public ResponseEntity<Coffee> addCoffee(@RequestBody Coffee coffee) {
        coffeeService.insert(coffee);
        return ResponseEntity.ok().body(coffee);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "delete one coffee based on id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "coffee removed with success"),
            @ApiResponse(responseCode = "500",description = "untreated exception")
    })
    public void removeById(@PathVariable Long id) {
        coffeeService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "update one coffee based on id, it will return a updated coffee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "update coffee success")
    })
    public ResponseEntity<Coffee> updateCoffeeById(@PathVariable Long id, @RequestBody Coffee newCoffee) {
        return ResponseEntity.ok().body(coffeeService.updateCoffeeById(id, newCoffee));
    }
}
