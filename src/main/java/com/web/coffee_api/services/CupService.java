package com.web.coffee_api.services;

import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.repositories.CupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
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

    public Cup insert(Cup cup) {
        if (cupRepository.findById(cup.getId()).isEmpty()) {
            Long generatedId = cupRepository.save(cup).getId();
            return cupRepository.findById(generatedId).orElseThrow();
        }
        throw new IllegalArgumentException("cup at id: " + cup.getId() + " already exists");
    }

    public void deleteById(Long id) {
        cupRepository.deleteById(id);
    }

    public Cup update(Long id, Cup newCup) {
        Cup oldCup = cupRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("cup at id: " + id + "can't be found"));

        oldCup.setName(newCup.getName());
        oldCup.getSizes().removeAll(oldCup.getSizes());
        oldCup.getSizes().addAll(newCup.getSizes());
        oldCup.getCoffeeCups().removeAll(oldCup.getCoffeeCups());
        oldCup.getCoffeeCups().addAll(newCup.getCoffeeCups());
        cupRepository.save(oldCup);

        return cupRepository.findById(id).orElseThrow();
    }
}
