package com.procol.procolombia.services.impl;

import com.procol.procolombia.entities.TipoEmpresa;
import com.procol.procolombia.entities.dto.EmpresaDto;
import com.procol.procolombia.repositories.EmpresaRepository;
import com.procol.procolombia.repositories.TipoEmpresaRepository;
import com.procol.procolombia.services.EmpresaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final TipoEmpresaRepository tipoEmpresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, TipoEmpresaRepository tipoEmpresaRepository) {
        this.empresaRepository = empresaRepository;
        this.tipoEmpresaRepository = tipoEmpresaRepository;
    }

    @Override
    public EmpresaDto createEmpresa(EmpresaDto empresaDto) {
        
    }

    @Override
    public EmpresaDto updateEmpresa(Integer id, EmpresaDto empresaDto) {
        return null;
    }

    @Override
    public void deleteEmpresa(Integer id) {

    }

    @Override
    public Optional<EmpresaDto> getEmpresaById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<EmpresaDto> getAllEmpresas() {
        return List.of();
    }

    @Override
    public List<EmpresaDto> getEmpresaByNombre(String nombre) {
        return List.of();
    }

    @Override
    public List<EmpresaDto> getEmpresaByTipoEmpresa(TipoEmpresa tipoEmpresa) {
        return List.of();
    }

    @Override
    public List<EmpresaDto> getEmpresaByTipoEmpresa(TipoEmpresa tipoEmpresa, Pageable pageable) {
        return List.of();
    }

    @Override
    public Page<EmpresaDto> getEmpresaByNombre(String nombre, Pageable pageable) {
        return null;
    }

    @Override
    public boolean existsEmpresaByNombre(String nombre) {
        return false;
    }

    @Override
    public List<EmpresaDto> getByUsuarioId(Integer usuarioId) {
        return List.of();
    }
}
