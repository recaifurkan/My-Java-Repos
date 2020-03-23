package com.rfbsoft.repository;

import com.rfbsoft.model.Neighborhood;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {
}
