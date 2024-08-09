package com.web.coffee_api.repositories;

import com.web.coffee_api.entities.CupSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CupSizeRepository extends JpaRepository<CupSize, Long> {
}
