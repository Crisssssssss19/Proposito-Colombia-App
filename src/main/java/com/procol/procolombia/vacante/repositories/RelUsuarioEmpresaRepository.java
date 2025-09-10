package com.procol.procolombia.vacante.repositories;

import com.procol.procolombia.vacante.entities.RelUsuarioEmpresa;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelUsuarioEmpresaRepository extends JpaRepository<RelUsuarioEmpresa, RelUsuarioEmpresaId> {
    Optional<RelUsuarioEmpresa> findByUsuarioId(Integer usuarioId);
    Optional<RelUsuarioEmpresa> findByEmpresaId(Integer empresaId);
    void deleteByUsuarioId(Integer usuarioId);
}