package com.web.coffee_api.controllers;

import com.web.coffee_api.entities.CupSize;
import com.web.coffee_api.services.CupSizeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/sizes")
@Tag(name = "Sizes")
public class CupSizeController {
    @Autowired
    private CupSizeService cupSizeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieves a Size entities list", method = "GET")
    public ResponseEntity<List<CupSize>> getAll() {
        return ResponseEntity.ok().body(cupSizeService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieves a Size entity by its id", method = "GET")
    public ResponseEntity<CupSize> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cupSizeService.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a Size entity and return it", method = "POST")
    public ResponseEntity<CupSize> insert(@RequestBody CupSize cupSize) {
        return ResponseEntity.ok().body(cupSizeService.insert(cupSize));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a Size entity by its id", method = "DELETE")
    public void deleteById (@PathVariable Long id) {
        cupSizeService.deleteById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a Size entity by its id and return the updated entity", method = "PUT", description = "this method cannot update the entity id")
    public ResponseEntity<CupSize> updateById(@PathVariable Long id,@RequestBody CupSize new_cupSize) {
        return ResponseEntity.ok().body(cupSizeService.updateById(id,new_cupSize));
    }
}
