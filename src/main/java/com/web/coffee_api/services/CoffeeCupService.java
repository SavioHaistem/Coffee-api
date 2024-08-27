package com.web.coffee_api.services;

import com.web.coffee_api.repositories.CoffeeCupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeCupService {
    @Autowired
    private CoffeeCupRepository cupRepository;
}
