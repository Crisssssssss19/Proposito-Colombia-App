package com.procol.procolombia.vacante.Services;

import com.procol.procolombia.vacante.dto.EmpresaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmpresaService {
    EmpresaDto createEmpresa(EmpresaDto empresaDto);
    EmpresaDto updateEmpresa(EmpresaDto empresaDto);
    void deleteEmpresa(EmpresaDto empresaDto);
    List<EmpresaDto> getAllEmpresas();
    List<EmpresaDto> getAllEmpresasByVacanteId(Integer vacanteId);
    Integer obtenerCantidadEmpresas();
    Page<EmpresaDto> buscarEmpresasFiltro (String filtro, Pageable pageable);
}
