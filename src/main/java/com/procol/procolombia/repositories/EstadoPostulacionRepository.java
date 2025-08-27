package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.EstadoPostulacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoPostulacionRepository extends JpaRepository<EstadoPostulacion, Integer> {
  }