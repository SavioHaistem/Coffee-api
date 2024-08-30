package com.web.coffee_api.services;

import com.web.coffee_api.entities.Coffee;
import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.repositories.CoffeeRepository;
import com.web.coffee_api.repositories.CupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository repository;
    @Autowired
    private CupRepository cupRepository;

    public Coffee insert(Coffee coffee) {
        if (repository.findById(coffee.getId()).isEmpty()) {
            Long generatedId = repository.save(coffee).getId();
            return repository.findById(generatedId).orElseThrow();
        }
        throw new IllegalArgumentException("coffee at id: " + coffee.getId() + "already exists");
    }

    public Coffee findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Coffee> findAll() {
        return repository.findAll();
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public Coffee updateCoffeeById(Long id, Coffee newCoffee) {
        //TODO: update cups
        Coffee updatedCoffee = repository.findById(id).orElseThrow();
        updatedCoffee.setName(newCoffee.getName());
        updatedCoffee.setPrice(newCoffee.getPrice());
        updatedCoffee.setDescription(newCoffee.getDescription());
        repository.save(updatedCoffee);
        return repository.findById(id).orElseThrow();
    }
}
