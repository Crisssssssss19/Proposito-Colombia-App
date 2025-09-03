package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.HistorialEstadosVacante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface HistorialEstadosVacanteRepository extends JpaRepository<HistorialEstadosVacante, Integer> {

    List<HistorialEstadosVacante> findByIdVacante_Id(Integer idVacante);

    List<HistorialEstadosVacante> findByIdEstadoVacante_Id(Integer idEstado);

    @Query("SELECT h FROM HistorialEstadosVacante h WHERE h.idVacante.id = :vacanteId ORDER BY h.fechaHistorialEstadoVacante DESC")
    List<HistorialEstadosVacante> findByVacanteOrdenado(@Param("vacanteId") Integer vacanteId);

    @Query("SELECT h FROM HistorialEstadosVacante h WHERE h.fechaHistorialEstadoVacante BETWEEN :fechaInicio AND :fechaFin")
    Page<HistorialEstadosVacante> findByFechaRange(@Param("fechaInicio") Instant fechaInicio, @Param("fechaFin") Instant fechaFin, Pageable pageable);

    @Query("SELECT COUNT(h) FROM HistorialEstadosVacante h WHERE h.idVacante.id = :vacanteId")
    long countByVacante(@Param("vacanteId") Integer vacanteId);

    @Query("SELECT h FROM HistorialEstadosVacante h JOIN h.idVacante v WHERE v.relUsuariosEmpresas.idEmpresa.id = :empresaId")
    List<HistorialEstadosVacante> findByEmpresa(@Param("empresaId") Integer empresaId);

}