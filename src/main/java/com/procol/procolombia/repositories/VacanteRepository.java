package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacanteRepository extends JpaRepository<Vacante, Integer> {
  }