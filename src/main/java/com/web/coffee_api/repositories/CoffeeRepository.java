package com.web.coffee_api.repositories;

import com.web.coffee_api.entities.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
