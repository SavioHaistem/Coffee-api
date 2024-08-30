package com.web.coffee_api.controllers;

import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.services.CupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cup> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cupService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        cupService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Cup> insert(@RequestBody Cup cup) {
        return ResponseEntity.ok().body(cupService.insert(cup));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cup> updateById(@PathVariable Long id, @RequestBody Cup new_cup) {
        return ResponseEntity.ok().body(cupService.updateById(id,new_cup));
    }
}
