package com.web.coffee_api.services;

import com.web.coffee_api.entities.CoffeeCup;
import com.web.coffee_api.entities.CupSize;
import com.web.coffee_api.repositories.CoffeeCupRepository;
import com.web.coffee_api.repositories.CoffeeRepository;
import com.web.coffee_api.repositories.CupRepository;
import com.web.coffee_api.repositories.CupSizeRepository;
import com.web.coffee_api.services.exceptions.ArgumentsException;
import com.web.coffee_api.services.exceptions.IllegalOperation;
import com.web.coffee_api.services.exceptions.ResourceNotFound;
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
        return coffeeCupRepository.findById(id).orElseThrow(()->
            new ResourceNotFound("can't find Coffee cup at id: " + id)
        );
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
        CoffeeCup old_coffeeCup = findById(id);

        old_coffeeCup.setCoffee(coffeeRepository.findById(
                new_coffeeCup.getCoffee().getId()).orElseThrow(()->
                new ResourceNotFound("the coffee at this coffee cup can't be found")));
        old_coffeeCup.setCup(cupRepository.findById(
                new_coffeeCup.getCup().getId()).orElseThrow(()->
                new ResourceNotFound("the cup at this coffee cup can't be found")));
        old_coffeeCup.setSize(old_coffeeCup.validSize(cupSizeRepository.findById(
                new_coffeeCup.getSize().getId()).orElseThrow(()->
                new ResourceNotFound("the size at this coffee cup can't be found"))));

        Long generatedId = coffeeCupRepository.save(old_coffeeCup).getId();
        return coffeeCupRepository.findById(generatedId).orElseThrow(()->
            new ResourceNotFound("can't find updated coffee at id: " + generatedId)
        );
    }

    public CoffeeCup insert(CoffeeCup coffeeCup) {
        //TODO: try improve this solution;
        Long generatedId = null;
        if (coffeeCupRepository.findById(coffeeCup.getId()).isEmpty()) {
            CupSize cupSize = cupSizeRepository.findById(coffeeCup.getSize().getId())
                    .orElseThrow(()-> new ResourceNotFound(
                            "can't find size at coffee on coffee cup, size id: "
                            + coffeeCup.getCup().getId())
                    );
            if (cupSize.getCups().contains(coffeeCup.getCup())) {
                generatedId = coffeeCupRepository.save(coffeeCup).getId();
            } else {
                throw new ArgumentsException(
                        "Invalid cup for size: "
                        + cupSize.getId(),
                        "caused by insert method at CoffeeCup service"
                        + ", this size cannot contain this size",
                        HttpStatus.BAD_REQUEST
                );
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
