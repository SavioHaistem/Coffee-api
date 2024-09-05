package com.web.coffee_api.controllers;

import com.web.coffee_api.entities.CupSize;
import com.web.coffee_api.services.CupSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sizes")
public class CupSizeController {
    @Autowired
    private CupSizeService cupSizeService;

    @GetMapping
    public ResponseEntity<List<CupSize>> getAll() {
        return ResponseEntity.ok().body(cupSizeService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CupSize> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(cupSizeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CupSize> insert(@RequestBody CupSize cupSize) {
        return ResponseEntity.ok().body(cupSizeService.insert(cupSize));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById (@PathVariable Long id) {
        cupSizeService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CupSize> updateById(@PathVariable Long id,@RequestBody CupSize new_cupSize) {
        return ResponseEntity.ok().body(cupSizeService.updateById(id,new_cupSize));
    }
}
