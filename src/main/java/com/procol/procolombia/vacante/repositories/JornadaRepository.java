package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JornadaRepository extends JpaRepository <Jornada, Integer> {
}
