package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequisitoRepository extends JpaRepository<Requisito, Integer> {

  List<Requisito> findByIdVacanteId(Integer idVacante);

}