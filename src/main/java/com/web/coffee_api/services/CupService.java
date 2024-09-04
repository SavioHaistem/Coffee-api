package com.web.coffee_api.services;

import com.web.coffee_api.entities.Cup;
import com.web.coffee_api.entities.CupSize;
import com.web.coffee_api.repositories.CupRepository;
import com.web.coffee_api.repositories.CupSizeRepository;
import com.web.coffee_api.services.exceptions.ArgumentsException;
import com.web.coffee_api.services.exceptions.IllegalOperation;
import com.web.coffee_api.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CupService implements ServiceBasics<Cup> {
    @Autowired
    private CupRepository cupRepository;
    @Autowired
    private CupSizeRepository sizeRepository;

    public List<Cup> findAll() {
        return cupRepository.findAll();
    }

    public Cup findById(Long id) {
        return cupRepository.findById(id).orElseThrow(() ->
                new ResourceNotFound("can't find cup at id: " + id)
        );
    }

    public Cup insert(Cup cup) {
        if (cupRepository.findById(cup.getId()).isEmpty()) {
            Set<CupSize> validSizes = new HashSet<>();
            for (CupSize size : cup.getSizes()) {
                CupSize validSize = sizeRepository.findById(size.getId()).orElseThrow(() ->
                        new ArgumentsException(
                        "size at id: "
                                + size.getId()
                                + " can't be found",
                        "caused by insert Cup method at CupService",
                        HttpStatus.NOT_ACCEPTABLE
                ));

                cup.getSizes().remove(size);
                cup.getSizes().add(validSize);
                validSize.addCup(cup);
                validSizes.add(validSize);
            }

            Long generatedId = cupRepository.save(cup).getId();
            sizeRepository.saveAll(validSizes);
            return cupRepository.findById(generatedId).orElseThrow();
        } else {
            throw new ArgumentsException(
                    "cup at id: "
                            + cup.getId()
                            + " already exists",
                    "caused by: insert method at CupService",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    public Cup updateById(Long id, Cup new_cup) {
        Cup old_cup = findById(id);

        old_cup.setName(new_cup.getName());
        old_cup.getCoffeeCups().removeAll(old_cup.getCoffeeCups());
        old_cup.getCoffeeCups().addAll(new_cup.getCoffeeCups());
        old_cup.getSizes().removeAll(old_cup.getSizes());
        
        for (CupSize size : new_cup.getSizes()) {
            CupSize validSize = sizeRepository.findById(size.getId()).orElseThrow(()->
                    new ArgumentsException(
                    "cannot associate this size at id: "
                            + size.getId()
                            + " because the size can't be found",
                    "caused by update Cup by id method at CupService",
                    HttpStatus.NOT_FOUND
            ));

            old_cup.getSizes().add(validSize);
            validSize.getCups().add(old_cup);
        }

        sizeRepository.saveAll(old_cup.getSizes());
        Long generatedId = cupRepository.save(old_cup).getId();

        return cupRepository.findById(generatedId).orElseThrow(() ->
                new ResourceNotFound("can't find updated cup at id: " + generatedId)
        );
    }

    public void deleteById(Long id) {
        try {
            cupRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalOperation("can't remove cup at id: "
                    + id
                    + " because this is associated with others entities"
                    , e.getMessage());
        }
    }
}
