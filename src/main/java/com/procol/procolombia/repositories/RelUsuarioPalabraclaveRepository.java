package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.RelUsuarioPalabraclave;
import com.procol.procolombia.entities.RelUsuarioPalabraclaveId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelUsuarioPalabraclaveRepository extends JpaRepository<RelUsuarioPalabraclave, RelUsuarioPalabraclaveId> {

    List<RelUsuarioPalabraclave> findByIdUsuario_Id(Integer idUsuario);

    List<RelUsuarioPalabraclave> findByIdPalabraClave_Id(Integer idPalabraClave);

    boolean existsByIdUsuario_IdAndIdPalabraClave_Id(Integer idUsuario, Integer idPalabraClave);

    @Query("SELECT COUNT(r) FROM RelUsuarioPalabraclave r WHERE r.idUsuario.id = :usuarioId")
    long countByUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT COUNT(r) FROM RelUsuarioPalabraclave r WHERE r.idPalabraClave.id = :palabraId")
    long countByPalabra(@Param("palabraId") Integer palabraId);

    void deleteByIdUsuario_IdAndIdPalabraClave_Id(Integer idUsuario, Integer idPalabraClave);

}