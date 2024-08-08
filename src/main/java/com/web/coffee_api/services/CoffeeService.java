package com.web.coffee_api.services;

import com.web.coffee_api.entities.Coffee;
import com.web.coffee_api.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository repository;

    public void insertCoffee(Coffee coffee) {
        repository.save(coffee);
        //TODO: check Coffee
    }
    public Coffee findById(Long id) {
        //TODO: orElseThrow personalized error
        return repository.findById(id).orElse(null);
    }

}
