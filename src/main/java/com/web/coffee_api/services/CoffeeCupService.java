package com.web.coffee_api.services;

import com.web.coffee_api.entities.CoffeeCup;
import com.web.coffee_api.repositories.CoffeeCupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeCupService {
    @Autowired
    private CoffeeCupRepository coffeeCupRepository;

    public List<CoffeeCup> findAll() {
        return coffeeCupRepository.findAll();
    }
}
