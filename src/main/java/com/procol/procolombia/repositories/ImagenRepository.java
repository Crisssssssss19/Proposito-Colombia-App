package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepository extends JpaRepository<Imagen, Integer> {
  }