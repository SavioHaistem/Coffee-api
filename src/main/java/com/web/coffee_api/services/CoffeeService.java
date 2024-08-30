package com.web.coffee_api.services;

import com.web.coffee_api.entities.Coffee;
import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.repositories.CoffeeRepository;
import com.web.coffee_api.repositories.CupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService implements ServiceBasics<Coffee> {
    @Autowired
    private CoffeeRepository repository;
    @Autowired
    private CupRepository cupRepository;

    public List<Coffee> findAll() {
        return repository.findAll();
    }

    public Coffee findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Coffee insert(Coffee coffee) {
        if (repository.findById(coffee.getId()).isEmpty()) {
            Long generatedId = repository.save(coffee).getId();
            return repository.findById(generatedId).orElseThrow();
        }
        throw new IllegalArgumentException("coffee at id: " + coffee.getId() + "already exists");
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Coffee updateById(Long id, Coffee new_coffee) {

        Coffee old_coffee = repository.findById(id).orElseThrow(()->
            new IllegalArgumentException("can't find coffee at id: " + id)
        );

        old_coffee.setName(new_coffee.getName());
        old_coffee.setPrice(new_coffee.getPrice());
        old_coffee.setDescription(new_coffee.getDescription());
        Long generatedId = repository.save(old_coffee).getId();

        return repository.findById(generatedId).orElseThrow();
    }
}
