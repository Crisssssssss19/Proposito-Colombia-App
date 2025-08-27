package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.PalabraClave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalabraClaveRepository extends JpaRepository<PalabraClave, Integer> {
  }