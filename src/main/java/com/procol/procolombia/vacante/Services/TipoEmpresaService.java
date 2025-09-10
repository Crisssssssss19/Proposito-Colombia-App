package com.procol.procolombia.vacante.Services;

import com.procol.procolombia.vacante.dto.TipoEmpresaDto;

import java.util.List;

public interface TipoEmpresaService {
    List<TipoEmpresaDto> getTipoEmpresas();
    TipoEmpresaDto findTipoEmpresaById(Integer tipoEmpresaId);
    TipoEmpresaDto UpdateTipoEmpresa(TipoEmpresaDto tipoEmpresaDto);
    void updateTipoEmpresa(TipoEmpresaDto tipoEmpresaDto);
    void deleteTipoEmpresa(Integer id);
}
