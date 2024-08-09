package com.web.coffee_api.services;

import com.web.coffee_api.entities.CupSize;
import com.web.coffee_api.repositories.CupSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupSizeService {
    @Autowired
    private CupSizeRepository cupSizeRepository;

    public CupSize findById(Long id) {
        return cupSizeRepository.findById(id).orElse(null);
    }

    public void add(CupSize cupSize) {
        cupSizeRepository.save(cupSize);
    }
}
