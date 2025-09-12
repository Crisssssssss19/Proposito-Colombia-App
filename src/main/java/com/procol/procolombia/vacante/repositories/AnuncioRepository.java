package com.procol.procolombia.vacante.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.procol.procolombia.vacante.entities.Anuncio;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
  Optional<Anuncio> findByIdVacante_Id(Integer idVacante);
}