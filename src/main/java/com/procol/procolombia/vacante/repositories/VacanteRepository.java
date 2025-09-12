package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.Vacante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Integer> {

    List<Vacante> findByRelUsuarioEmpresasIdEmpresaId(Integer empresaId);
    @Query("SELECT v FROM Vacante v " +
            "WHERE v.relUsuarioEmpresas.id.idUsuario = :idUsuario " +
            "AND v.relUsuarioEmpresas.id.idEmpresa = :idEmpresa")
    List<Vacante> findByUsuarioAndEmpresa(@Param("idUsuario") Integer idUsuario,
                                          @Param("idEmpresa") Integer idEmpresa);
}