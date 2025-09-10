package com.procol.procolombia.vacante.Services;

import com.procol.procolombia.vacante.dto.PalabrasClaveDto;
import com.procol.procolombia.vacante.dto.RelUsuarioEmpresaDto;

import java.util.List;

public interface RelUsuarioEmpresaService {
    List<RelUsuarioEmpresaDto> getAllUsuariosEmpresas();
    RelUsuarioEmpresaDto findUsuarioEmpresasByUsuarioId(Integer usuarioId);
    RelUsuarioEmpresaDto findUsuarioEmpresaByEmpresaId(Integer empresaId);
    void deleteUsuarioEmpresasByUsuarioId(Integer usuarioId, Integer empresaId);
}
