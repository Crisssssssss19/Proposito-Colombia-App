package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.EstadoVacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadoVacanteRepository extends JpaRepository<EstadoVacante, Integer> {
    List<EstadoVacante> findByEstadoVacantesId(Integer vacanteId);

}