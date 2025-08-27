package com.procol.procolombia.repositories;

import com.procol.procolombia.entities.RelUsuarioEmpresa;
import com.procol.procolombia.entities.idCompuestas.RelUsuarioEmpresaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelUsuarioEmpresaRepository extends JpaRepository<RelUsuarioEmpresa, RelUsuarioEmpresaId> {
}