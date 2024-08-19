package com.web.coffee_api.repositories;

import com.web.coffee_api.entities.CupSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupSizeRepository extends JpaRepository<CupSize,Long> {
}
