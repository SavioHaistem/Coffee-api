package com.web.coffee_api.services;

import com.web.coffee_api.entities.CoffeeCup;
import com.web.coffee_api.entities.CupSize;
import com.web.coffee_api.repositories.CoffeeCupRepository;
import com.web.coffee_api.repositories.CoffeeRepository;
import com.web.coffee_api.repositories.CupRepository;
import com.web.coffee_api.repositories.CupSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public void insert(CoffeeCup coffeeCup) {
        CupSize cupSize = cupSizeRepository.findById(coffeeCup.getSize().getId()).orElse(null);
        assert cupSize != null;
        if (cupSize.getCups().contains(coffeeCup.getCup())) {
            coffeeCupRepository.save(coffeeCup);
        } else {
            throw new IllegalArgumentException("invalid size for cup: " + coffeeCup.getId());
        }
    }
}
