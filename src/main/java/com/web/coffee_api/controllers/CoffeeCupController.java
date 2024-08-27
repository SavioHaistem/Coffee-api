package com.web.coffee_api.controllers;

import com.web.coffee_api.entities.CoffeeCup;
import com.web.coffee_api.services.CoffeeCupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/coffees_cups")
@Tag(name = "coffee-api")
public class CoffeeCupController {
    @Autowired
    private CoffeeCupService coffeeCupService;

    @GetMapping
    public ResponseEntity<List<CoffeeCup>> getAll() {
        return ResponseEntity.ok().body(coffeeCupService.findAll());
    }
}
