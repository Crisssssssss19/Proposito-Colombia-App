package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.RelVacantePalabraclave;
import com.procol.procolombia.vacante.entities.RelVacantePalabraclaveId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelVacantePalabraclaveRepository extends JpaRepository<RelVacantePalabraclave, RelVacantePalabraclaveId> {

    List<RelVacantePalabraclave> findByIdVacante_Id(Integer idVacante);

    List<RelVacantePalabraclave> findByIdPalabraClave_Id(Integer idPalabraClave);

    boolean existsByIdVacante_IdAndIdPalabraClave_Id(Integer idVacante, Integer idPalabraClave);

    @Query("SELECT COUNT(r) FROM RelVacantePalabraclave r WHERE r.idVacante.id = :vacanteId")
    long countByVacante(@Param("vacanteId") Integer vacanteId);

    @Query("SELECT COUNT(r) FROM RelVacantePalabraclave r WHERE r.idPalabraClave.id = :palabraId")
    long countByPalabra(@Param("palabraId") Integer palabraId);

    void deleteByIdVacante_IdAndIdPalabraClave_Id(Integer idVacante, Integer idPalabraClave);

}