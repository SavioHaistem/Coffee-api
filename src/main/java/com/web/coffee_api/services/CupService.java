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

import java.util.List;

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
        return cupRepository.findById(id).orElseThrow(()->
                new ResourceNotFound("can't find cup at id: " + id)
        );
    }

    public Cup insert(Cup cup) {
        if (cupRepository.findById(cup.getId()).isEmpty()) {
            cup.getSizes().forEach(System.out::println);
            sizeRepository.saveAll(cup.getSizes());
            Long generatedId = cupRepository.save(cup).getId();
            return cupRepository.findById(generatedId).orElseThrow();
        }
        throw new ArgumentsException(
                "cup at id: "
                + cup.getId()
                + " already exists",
                "caused by: insert method at CupService",
                HttpStatus.CONFLICT
        );
    }

    public Cup updateById(Long id, Cup new_cup) {
        Cup old_cup = findById(id);

        old_cup.setName(new_cup.getName());
        old_cup.getCoffeeCups().removeAll(old_cup.getCoffeeCups());
        old_cup.getCoffeeCups().addAll(new_cup.getCoffeeCups());
        old_cup.getSizes().removeAll(old_cup.getSizes());
        old_cup.getSizes().addAll(new_cup.getSizes());
        Long generatedId = cupRepository.save(old_cup).getId();

        return cupRepository.findById(generatedId).orElseThrow(()->
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
                    ,e.getMessage());
        }
    }
}
