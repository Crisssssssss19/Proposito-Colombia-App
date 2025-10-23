package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.RelUsuarioEmpresaDto;

import java.util.List;

public interface RelUsuarioEmpresaService {
    List<RelUsuarioEmpresaDto> getAllUsuariosEmpresas();
    RelUsuarioEmpresaDto findUsuarioEmpresasByUsuarioId(Integer usuarioId);
    RelUsuarioEmpresaDto findUsuarioEmpresaByEmpresaId(Integer empresaId);
    RelUsuarioEmpresaDto findUsuarioEmpresaByEmpresaIdAndUsuarioId(Integer empresaId, Integer usuarioId);
    RelUsuarioEmpresaDto createRelUsuarioEmpresa(RelUsuarioEmpresaDto relUsuarioEmpresaDto);
    void deleteUsuarioEmpresasByUsuarioId(Integer usuarioId, Integer empresaId);
}
