package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

  Optional<Anuncio> findByVacantes_Id(Integer idVacante);

  List<Anuncio> findByTipoAnuncio(String tipoAnuncio);

  List<Anuncio> findByTamanioAnuncio(String tamanioAnuncio);

  List<Anuncio> findByNombrePublicoAnuncioContainingIgnoreCase(String nombre);

  @Query("SELECT a FROM Anuncio a JOIN a.idVacante v WHERE v.estadoVacante = 1")
  List<Anuncio> findAnunciosActivos();

  @Query("SELECT COUNT(a) FROM Anuncio a WHERE a.tipoAnuncio = :tipo")
  long countByTipo(@Param("tipo") String tipo);

  boolean existsByVacantes_Id(Integer idVacante);

}