package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.HistorialEstadosPostulacione;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface HistorialEstadosPostulacioneRepository extends JpaRepository<HistorialEstadosPostulacione, Integer> {

    List<HistorialEstadosPostulacione> findByIdPostulacion_Id(Integer idPostulacion);

    List<HistorialEstadosPostulacione> findByIdEstadoPostulacion_Id(Integer idEstado);

    @Query("SELECT h FROM HistorialEstadosPostulacione h WHERE h.idPostulacion.id = :postulacionId ORDER BY h.fechaHistorialPostulacion DESC")
    List<HistorialEstadosPostulacione> findByPostulacionOrdenado(@Param("postulacionId") Integer postulacionId);

    @Query("SELECT h FROM HistorialEstadosPostulacione h WHERE h.fechaHistorialPostulacion BETWEEN :fechaInicio AND :fechaFin")
    Page<HistorialEstadosPostulacione> findByFechaRange(@Param("fechaInicio") Instant fechaInicio, @Param("fechaFin") Instant fechaFin, Pageable pageable);

    @Query("SELECT COUNT(h) FROM HistorialEstadosPostulacione h WHERE h.idPostulacion.id = :postulacionId")
    long countByPostulacion(@Param("postulacionId") Integer postulacionId);

    @Query("SELECT h FROM HistorialEstadosPostulacione h WHERE h.detalleHistorialPostulacion LIKE %:detalle%")
    List<HistorialEstadosPostulacione> findByDetalleContaining(@Param("detalle") String detalle);

}