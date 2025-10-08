package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.HistorialInteres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialInteresRepository extends JpaRepository<HistorialInteres, Integer> {
}
