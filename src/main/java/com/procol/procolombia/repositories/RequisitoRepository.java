package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequisitoRepository extends JpaRepository<Requisito, Integer> {

  List<Requisito> findByIdVacante_Id(Integer idVacante);

  @Query("SELECT r FROM Requisito r WHERE r.idVacante.id = :vacanteId ORDER BY r.ordenRequisito ASC")
  List<Requisito> findByVacanteOrdenado(@Param("vacanteId") Integer vacanteId);

  @Query("SELECT COUNT(r) FROM Requisito r WHERE r.idVacante.id = :vacanteId")
  long countByVacante(@Param("vacanteId") Integer vacanteId);

  List<Requisito> findByTituloRequisitoContainingIgnoreCase(String titulo);

  List<Requisito> findByDetalleRequisitoContainingIgnoreCase(String detalle);

}