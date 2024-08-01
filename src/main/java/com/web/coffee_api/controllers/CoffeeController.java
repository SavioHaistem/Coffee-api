package com.web.coffee_api.controllers;

import com.web.coffee_api.entities.Coffee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class CoffeeController {
    @GetMapping
    public ResponseEntity<Coffee> getById() {
        return ResponseEntity.ok().body(new Coffee(1L,"lunatic coffee","lunar coffee",1.0,10.0));
    }
}
