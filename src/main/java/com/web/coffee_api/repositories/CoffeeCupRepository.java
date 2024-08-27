package com.web.coffee_api.repositories;

import com.web.coffee_api.entities.CoffeeCup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeCupRepository extends JpaRepository<CoffeeCup,Long> {
}
