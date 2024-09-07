package com.web.coffee_api.controllers;

import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.services.CupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/cups")
@Tag(name = "Cups")
public class CupController {
    @Autowired
    private CupService cupService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieves a Cup entity list", method = "GET")
    public ResponseEntity<List<Cup>> getAll() {
        return ResponseEntity.ok().body(cupService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieves a Cup by its id", method = "GET")
    public ResponseEntity<Cup> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cupService.findById(id));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a Cup by its id", method = "DELETE")
    public void deleteById(@PathVariable Long id) {
        cupService.deleteById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a Cup entity and return it", method = "POST")
    public ResponseEntity<Cup> insert(@RequestBody Cup cup) {
        return ResponseEntity.ok().body(cupService.insert(cup));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a Cup by its id and return the updated entity", method = "UPDATE", description = "this method cannot update the entity id")
    public ResponseEntity<Cup> updateById(@PathVariable Long id, @RequestBody Cup new_cup) {
        return ResponseEntity.ok().body(cupService.updateById(id,new_cup));
    }
}
