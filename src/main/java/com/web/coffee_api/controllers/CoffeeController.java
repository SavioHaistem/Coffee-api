package com.web.coffee_api.controllers;

import com.web.coffee_api.entities.Coffee;
import com.web.coffee_api.services.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/coffees")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Coffee> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(coffeeService.findById(id));
    }
}
