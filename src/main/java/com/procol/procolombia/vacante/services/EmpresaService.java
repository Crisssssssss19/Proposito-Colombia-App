package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.EmpresaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmpresaService {
    EmpresaDto createEmpresa(EmpresaDto empresaDto);
    EmpresaDto updateEmpresa(Integer empresaId, EmpresaDto empresaDto);
    void deleteEmpresa(EmpresaDto empresaDto);
    List<EmpresaDto> getAllEmpresas();
    Integer obtenerCantidadEmpresas();
    Page<EmpresaDto> buscarEmpresasFiltro (String filtro, Pageable pageable);
    List<EmpresaDto> getEmpresasByTipoEmpresas(Integer tipoEmpresaId);
}
