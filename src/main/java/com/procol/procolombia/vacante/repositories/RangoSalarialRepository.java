package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.RangoSalarial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RangoSalarialRepository extends JpaRepository<RangoSalarial, Integer> {
}
