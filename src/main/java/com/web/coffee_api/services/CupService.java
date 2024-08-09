package com.web.coffee_api.services;

import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.repositories.CupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupService {
    @Autowired
    private CupRepository cupRepository;

    public Cup findById(Long id) {
        return cupRepository.findById(id).orElse(null);
    }

    public void put(Cup cup) {
        cupRepository.save(cup);
    }
}
