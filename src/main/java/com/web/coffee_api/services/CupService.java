package com.web.coffee_api.services;

import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.repositories.CupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CupService {
    @Autowired
    private CupRepository cupRepository;

    @EntityGraph(attributePaths = {"coffees","cupSizes"})
    public Cup findById(Long id) {
        return cupRepository.findById(id).orElseThrow();
    }
    
    public void put(Cup cup) {
        cupRepository.save(cup);
    }

    public List<Cup> findAll() {
        return cupRepository.findAll();
    }
}
