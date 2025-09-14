package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.services.EmpresaService;
import com.procol.procolombia.vacante.dto.EmpresaDto;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.TipoEmpresa;
import com.procol.procolombia.vacante.mappers.EmpresaMapper;
import com.procol.procolombia.vacante.repositories.EmpresaRepository;
import com.procol.procolombia.vacante.repositories.TipoEmpresaRepository;
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
    private final TipoEmpresaRepository tipoEmpresaRepository;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper, TipoEmpresaRepository tipoEmpresaRepository) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
        this.tipoEmpresaRepository = tipoEmpresaRepository;
    }

    @Override
    public EmpresaDto createEmpresa(EmpresaDto empresaDto) {
        Empresa entity = empresaMapper.toEntity(empresaDto);
        Empresa saved = empresaRepository.save(entity);
        return empresaMapper.toDto(saved);
    }
    @Override
    public EmpresaDto updateEmpresa(Integer id,EmpresaDto empresaDto) {
        Empresa existing = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada: " + id));

        TipoEmpresa tipoEmpresa = tipoEmpresaRepository.findById(empresaDto.idTipoEmpresa())
                .orElseThrow(() -> new RuntimeException("Tipo de empresa no encontrada con id: " + empresaDto.idTipoEmpresa()));

        existing.setNombreEmpresa(empresaDto.nombreEmpresa());
        existing.setDireccionEmpresa(empresaDto.direccionEmpresa());
        existing.setTelefonoEmpresa(empresaDto.telefonoEmpresa());
        existing.setIdTipoEmpresa(tipoEmpresa);

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
    public Integer obtenerCantidadEmpresas() {
        return (int) empresaRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmpresaDto> buscarEmpresasFiltro(String filtro, Pageable pageable) {
        return empresaRepository.findBynombreEmpresaContainingIgnoreCase(filtro, pageable)
                .map(empresaMapper::toDto);
    }
    @Override
    public List<EmpresaDto> getEmpresasByTipoEmpresas(Integer tipoEmpresaId) {
        return empresaRepository.findEmpresasByTipoEmpresaId(tipoEmpresaId)
                .stream()
                .map(empresaMapper::toDto)
                .toList();
    }
}
