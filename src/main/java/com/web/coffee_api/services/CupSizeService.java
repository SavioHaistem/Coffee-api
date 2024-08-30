package com.web.coffee_api.services;

import com.web.coffee_api.entities.CupSize;
import com.web.coffee_api.repositories.CupSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CupSizeService implements ServiceBasics<CupSize> {
    @Autowired
    private CupSizeRepository cupSizeRepository;

    public List<CupSize> findAll() {
        return cupSizeRepository.findAll();
    }

    public CupSize findById(Long id) {
        return cupSizeRepository.findById(id).orElse(null);
    }

    public CupSize insert(CupSize cupSize) {
        if (cupSizeRepository.findById(cupSize.getId()).isEmpty()) {
            Long generatedId = cupSizeRepository.save(cupSize).getId();
            return cupSizeRepository.findById(generatedId).orElseThrow();
        }
        throw new IllegalArgumentException("size at id: " + cupSize.getId() + " already exists");
    }

    public void deleteById(Long id) {
        cupSizeRepository.deleteById(id);
    }

    public CupSize updateById(Long id, CupSize new_cupSize) {
        CupSize old_cupSize = cupSizeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("can't find size at id: " + id)
        );

        old_cupSize.setSize(new_cupSize.getSize());
        Long generatedId = cupSizeRepository.save(old_cupSize).getId();

        return cupSizeRepository.findById(generatedId).orElseThrow();
    }
}
