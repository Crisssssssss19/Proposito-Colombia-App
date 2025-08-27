package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngresoRepository extends JpaRepository<Ingreso, Integer> {
}