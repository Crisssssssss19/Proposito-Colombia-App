package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.TipoEmpresaDto;

public interface TipoEmpresaService {
    TipoEmpresaDto findTipoEmpresaById(Integer tipoEmpresaId);
    TipoEmpresaDto updateTipoEmpresa(Integer id, TipoEmpresaDto tipoEmpresaDto);
    void deleteTipoEmpresa(Integer id);
}
