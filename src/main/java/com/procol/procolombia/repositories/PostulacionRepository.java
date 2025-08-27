package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostulacionRepository extends JpaRepository<Postulacion, Integer> {
  }