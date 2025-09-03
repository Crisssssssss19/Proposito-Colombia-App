package com.procol.procolombia.services;

import com.procol.procolombia.entities.Empresa;
import com.procol.procolombia.entities.TipoEmpresa;
import com.procol.procolombia.entities.dto.EmpresaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {
    EmpresaDto createEmpresa(EmpresaDto empresaDto);
    EmpresaDto updateEmpresa(Integer id, EmpresaDto empresaDto);
    void deleteEmpresa(Integer id);
    Optional<EmpresaDto> getEmpresaById(Integer id);
    List<EmpresaDto> getAllEmpresas();
    List<EmpresaDto> getEmpresaByNombre(String nombre);
    List<EmpresaDto> getEmpresaByTipoEmpresa(TipoEmpresa tipoEmpresa);
    List<EmpresaDto> getEmpresaByTipoEmpresa(TipoEmpresa tipoEmpresa, Pageable pageable);
    Page<EmpresaDto> getEmpresaByNombre(String nombre, Pageable pageable);
    boolean existsEmpresaByNombre(String nombre);
    List<EmpresaDto> getByUsuarioId(Integer usuarioId);

}
