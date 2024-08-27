package com.web.coffee_api.controllers;

import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.services.CupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cups")
@Tag(name = "coffee-api")
public class CupController {
    @Autowired
    private CupService cupService;

    @GetMapping
    public ResponseEntity<List<Cup>> getAll() {
        return ResponseEntity.ok().body(cupService.findAll());
    }
}
