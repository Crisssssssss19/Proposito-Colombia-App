package com.procol.procolombia.vacante.Services.Impl;

import com.procol.procolombia.vacante.Services.EmpresaService;
import com.procol.procolombia.vacante.dto.EmpresaDto;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.TipoEmpresa;
import com.procol.procolombia.vacante.mappers.EmpresaMapper;
import com.procol.procolombia.vacante.repositories.EmpresaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
    }

    @Override
    public EmpresaDto createEmpresa(EmpresaDto empresaDto) {
        Empresa entity = empresaMapper.toEntity(empresaDto);
        Empresa saved = empresaRepository.save(entity);
        return empresaMapper.toDto(saved);
    }
    @Override
    public EmpresaDto updateEmpresa(EmpresaDto empresaDto) {
        if (empresaDto.id() == null) {
            throw new IllegalArgumentException("El ID de la empresa no puede ser nulo para actualizar");
        }

        Empresa existing = empresaRepository.findById(empresaDto.id())
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        existing.setNombreEmpresa(empresaDto.nombreEmpresa());
        existing.setDireccionEmpresa(empresaDto.direccionEmpresa());
        existing.setTelefonoEmpresa(empresaDto.telefonoEmpresa());

        if (empresaDto.idTipoEmpresa() != null) {
            TipoEmpresa tipo = new TipoEmpresa();
            tipo.setId(empresaDto.idTipoEmpresa());
            existing.setIdTipoEmpresa(tipo);
        }

        Empresa updated = empresaRepository.save(existing);
        return empresaMapper.toDto(updated);
    }
    @Override
    public void deleteEmpresa(EmpresaDto empresaDto) {
        if (empresaDto.id() == null) {
            throw new IllegalArgumentException("El ID de la empresa no puede ser nulo para eliminar");
        }
        empresaRepository.deleteById(empresaDto.id());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpresaDto> getAllEmpresas() {
        return empresaRepository.findAll()
                .stream()
                .map(empresaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmpresaDto> getAllEmpresasByVacanteId(Integer vacanteId) {
        return empresaRepository.findByVacantesId(vacanteId)
                .stream()
                .map(empresaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Integer obtenerCantidadEmpresas() {

        return (int) empresaRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmpresaDto> buscarEmpresasFiltro(String filtro, Pageable pageable) {
        return empresaRepository.findByNombreContainingIgnoreCase(filtro, pageable)
                .map(empresaMapper::toDto);
    }
}
