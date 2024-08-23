package com.web.coffee_api.services;

import com.web.coffee_api.entities.Coffee;
import com.web.coffee_api.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeRepository repository;

    public void insert(Coffee coffee) {
        repository.save(coffee);
    }
    public Coffee findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public List<Coffee> findAll() {
        return repository.findAll();
    }
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
