package com.web.coffee_api.services;

import com.web.coffee_api.entities.CupSize;
import com.web.coffee_api.repositories.CupSizeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;

@Service
@Transactional
public class CupSizeService implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Autowired
    private CupSizeRepository cupSizeRepository;

    public CupSize findById(Long id) {
        return cupSizeRepository.findById(id).orElse(null);
    }

    public void add(CupSize cupSize) {
        cupSizeRepository.save(cupSize);
    }
}
