package com.web.coffee_api.services;

import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.repositories.CupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

@Service
public class CupService {
    @Autowired
    private CupRepository cupRepository;

    @EntityGraph(attributePaths = {"coffees","cupSizes"})
    public Cup findById(Long id) {
        Cup cup = cupRepository.findById(id).orElse(null);
        System.out.println("cuffs: " + (cup != null ? cup.getCoffees().size() : "null"));
        return cup;
    }
    
    public void put(Cup cup) {
        cupRepository.save(cup);
    }
}
