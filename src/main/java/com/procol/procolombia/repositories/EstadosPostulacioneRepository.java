package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.EstadosPostulacione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadosPostulacioneRepository extends JpaRepository<EstadosPostulacione, Integer> {

    Optional<EstadosPostulacione> findByNombreEstadoPostulacion(String nombreEstado);

    List<EstadosPostulacione> findByOrderByOrdenEstadoPostulacionAsc();

    Optional<EstadosPostulacione> findByOrdenEstadoPostulacion(Short orden);

    @Query("SELECT COUNT(h) FROM HistorialEstadosPostulacione h WHERE h.idEstadoPostulacion.id = :estadoId")
    long countHistorialByEstado(@Param("estadoId") Integer estadoId);

    @Query("SELECT e FROM EstadosPostulacione e WHERE e.nombreEstadoPostulacion LIKE %:nombre%")
    List<EstadosPostulacione> findByNombreContaining(@Param("nombre") String nombre);

    boolean existsByNombreEstadoPostulacion(String nombreEstado);

}