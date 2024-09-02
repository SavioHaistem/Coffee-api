package com.web.coffee_api.services;

import com.web.coffee_api.entities.CoffeeCup;
import com.web.coffee_api.entities.CupSize;
import com.web.coffee_api.repositories.CoffeeCupRepository;
import com.web.coffee_api.repositories.CoffeeRepository;
import com.web.coffee_api.repositories.CupRepository;
import com.web.coffee_api.repositories.CupSizeRepository;
import com.web.coffee_api.services.exceptions.ArgumentsException;
import com.web.coffee_api.services.exceptions.IllegalOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
        try {
            coffeeCupRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalOperation(
                    "can't remove Coffee Cup at id: "
                    + id
                    + " because this is associated with others entities",
                    e.getMessage()
            );
        }
    }

    public CoffeeCup updateById(Long id, CoffeeCup new_coffeeCup) {
        CoffeeCup old_coffeeCup = coffeeCupRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("can't find coffee cup at id: " + id)
        );

        //TODO: check if update size test the cupSize is valid;

        old_coffeeCup.setCoffee(coffeeRepository.findById(new_coffeeCup.getCoffee().getId()).orElseThrow(()->
                new IllegalArgumentException("the coffee at this coffee cup can't be found")));
        old_coffeeCup.setCup(cupRepository.findById(new_coffeeCup.getCup().getId()).orElseThrow(()->
                new IllegalArgumentException("the cup at this coffee cup can't be found")));
        old_coffeeCup.setSize(old_coffeeCup.validSize(cupSizeRepository.findById(new_coffeeCup.getSize().getId()).
                orElseThrow(()->
                new IllegalArgumentException("the size at this cup is invalid"))));
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
            throw new ArgumentsException("Coffee cup at id: "
                    + coffeeCup.getId() +
                    " already exists",
                    " caused by insert method at CoffeeCupService",
                    HttpStatus.CONFLICT
            );
        }

        return coffeeCupRepository.findById(generatedId).orElseThrow();
    }
}
