package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.EstadosVacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadosVacanteRepository extends JpaRepository<EstadosVacante, Integer> {

    Optional<EstadosVacante> findByNombreEstadoVacante(String nombreEstado);

    List<EstadosVacante> findByOrderByOrdenEstadoVacanteAsc();

    Optional<EstadosVacante> findByOrdenEstadoVacante(Short orden);

    @Query("SELECT COUNT(h) FROM HistorialEstadosVacante h WHERE h.idEstadoVacante.id = :estadoId")
    long countHistorialByEstado(@Param("estadoId") Integer estadoId);

    @Query("SELECT e FROM EstadosVacante e WHERE e.nombreEstadoVacante LIKE %:nombre%")
    List<EstadosVacante> findByNombreContaining(@Param("nombre") String nombre);

    boolean existsByNombreEstadoVacante(String nombreEstado);

}