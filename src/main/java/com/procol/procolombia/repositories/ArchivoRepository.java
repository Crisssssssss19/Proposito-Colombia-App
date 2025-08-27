package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {
  }