package com.web.coffee_api.services;

import com.web.coffee_api.entities.Coffee;
import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.repositories.CoffeeRepository;
import com.web.coffee_api.repositories.CupRepository;
import com.web.coffee_api.services.exceptions.ArgumentsException;
import com.web.coffee_api.services.exceptions.IllegalOperation;
import com.web.coffee_api.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
        return repository.findById(id).orElseThrow(()->
            new ResourceNotFound("can't find coffee at id: " + id)
        );
    }

    public Coffee insert(Coffee coffee) {
        if (repository.findById(coffee.getId()).isEmpty()) {
            Long generatedId = repository.save(coffee).getId();
            return repository.findById(generatedId).orElseThrow();
        }
        throw new ArgumentsException(
                "coffee at id: "
                + coffee.getId()
                + "already exists",
                "caused by insert method at CoffeeService",
                HttpStatus.CONFLICT
        );
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalOperation(
                    "can't remove coffee at id: "
                    + id
                    + " because this is associated with others entities",
                    e.getMessage()
            );
        }
    }

    public Coffee updateById(Long id, Coffee new_coffee) {
        Coffee old_coffee = findById(id);

        old_coffee.setName(new_coffee.getName());
        old_coffee.setPrice(new_coffee.getPrice());
        old_coffee.setDescription(new_coffee.getDescription());
        Long generatedId = repository.save(old_coffee).getId();

        return repository.findById(generatedId).orElseThrow(()->
             new ResourceNotFound("can't find updated coffee at id: " + generatedId)
        );
    }
}
