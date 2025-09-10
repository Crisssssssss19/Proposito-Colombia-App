package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.HistorialEstadoVacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistorialEstadoVacanteRepository extends JpaRepository<HistorialEstadoVacante, Integer> {
    Optional<HistorialEstadoVacante> findTopByVacanteIdOrderByFechaDesc(Integer vacanteId);
    List<HistorialEstadoVacante> findByVacanteIdOrderByFechaDesc(Integer vacanteId);
    List<HistorialEstadoVacante> findByEstadoId(Integer estadoId);
}