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

import java.util.Set;

@RestController
@RequestMapping(value = "/coffees")
@Tag(name = "coffee-api")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CupService cupService;

    @Operation(summary = "search for a coffee that has the given id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "coffee found with success"),
            @ApiResponse(responseCode = "404", description = "coffee not found"),
            @ApiResponse(responseCode = "500", description = "erro ainda nao tratado")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Coffee> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(coffeeService.findById(id));
    }

    @PostMapping
    @Operation(summary = "insert one coffee on coffees database", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create coffee operation sucess"),
    })
    public ResponseEntity<Coffee> addCoffee(@RequestBody Coffee coffee) {
        coffeeService.insert(coffee);
        return ResponseEntity.ok().body(coffee);
    }
}
