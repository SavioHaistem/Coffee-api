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
public class CoffeeCupService implements ServiceBasics<CoffeeCup> {
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

    public CoffeeCup updateById(Long id, CoffeeCup new_coffeeCup) {
        CoffeeCup old_coffeeCup = coffeeCupRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("can't find coffee cup at id: " + id)
        );

        //TODO: check if update size test the cupSize is valid;

        old_coffeeCup.setCoffee(new_coffeeCup.getCoffee());
        old_coffeeCup.setCup(new_coffeeCup.getCup());
        old_coffeeCup.setSize(old_coffeeCup.validSize(new_coffeeCup.getSize()));
        Long generatedId = coffeeCupRepository.save(old_coffeeCup).getId();

        return coffeeCupRepository.findById(generatedId).orElseThrow();
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
