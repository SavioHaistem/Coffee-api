package com.web.coffee_api.services;

import com.web.coffee_api.entities.CoffeeCup;
import com.web.coffee_api.entities.CupSize;
import com.web.coffee_api.repositories.CoffeeCupRepository;
import com.web.coffee_api.repositories.CoffeeRepository;
import com.web.coffee_api.repositories.CupRepository;
import com.web.coffee_api.repositories.CupSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeCupService {
    @Autowired
    private CoffeeCupRepository coffeeCupRepository;
    @Autowired
    private CoffeeRepository coffeeRepository;
    @Autowired
    private CupRepository cupRepository;
    @Autowired
    private CupSizeRepository cupSizeRepository;

    public List<CoffeeCup> findAll() {
        return coffeeCupRepository.findAll();
    }

    public CoffeeCup findById(Long id) {
        return coffeeCupRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        coffeeCupRepository.deleteById(id);
    }

    public CoffeeCup insert(CoffeeCup coffeeCup) {
        Long generatedId = null;
        if (coffeeCupRepository.findById(coffeeCup.getId()).isEmpty()) {
            CupSize cupSize = cupSizeRepository.findById(coffeeCup.getSize().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Cup at id: " +
                            coffeeCup.getCup().getId())
                    );
            if (cupSize.getCups().contains(coffeeCup.getCup())) {
                generatedId = coffeeCupRepository.save(coffeeCup).getId();
            } else {
                throw new IllegalArgumentException("Invalid size for cup: " + coffeeCup.getCup().getId());
            }
        } else {
            throw new IllegalArgumentException("Coffee cup on id: " + coffeeCup.getId() + " already exists");
        }

        return coffeeCupRepository.findById(generatedId).orElseThrow();
    }
}
