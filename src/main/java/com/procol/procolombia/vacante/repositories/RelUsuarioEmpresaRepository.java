package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.RelUsuarioEmpresa;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelUsuarioEmpresaRepository extends JpaRepository<RelUsuarioEmpresa, RelUsuarioEmpresaId> {
    Optional<RelUsuarioEmpresa> findById_IdUsuario(Integer idUsuario);
    Optional<RelUsuarioEmpresa> findById_IdEmpresa(Integer idEmpresa);
    @Query("SELECT rel FROM RelUsuarioEmpresa rel WHERE rel.idEmpresa = :idEmpresa AND rel.idUsuario = :idUsuario")
    Optional<RelUsuarioEmpresa> findByIdUsuarioAndIdEmpresa(Integer idUsuario, Integer idEmpresa);
    void deleteById_IdUsuarioAndId_IdEmpresa(Integer idUsuario, Integer idEmpresa);
}