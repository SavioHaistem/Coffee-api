package com.web.coffee_api.repositories;

import com.web.coffee_api.entities.Cup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupRepository extends JpaRepository<Cup, Long> {
}
