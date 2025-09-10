package com.procol.procolombia.vacante.Services.Impl;

import com.procol.procolombia.vacante.Services.TipoEmpresaService;
import com.procol.procolombia.vacante.dto.TipoEmpresaDto;
import com.procol.procolombia.vacante.entities.TipoEmpresa;
import com.procol.procolombia.vacante.mappers.TipoEmpresaMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.procol.procolombia.vacante.repositories.TipoEmpresaRepository;

import java.util.List;

@Service
public class TipoEmpresaServiceImpl implements TipoEmpresaService {

    private final TipoEmpresaRepository tipoEmpresaRepository;
    private final TipoEmpresaMapper tipoEmpresaMapper;

    public TipoEmpresaServiceImpl(TipoEmpresaRepository tipoEmpresaRepository,
                                  TipoEmpresaMapper tipoEmpresaMapper) {
        this.tipoEmpresaRepository = tipoEmpresaRepository;
        this.tipoEmpresaMapper = tipoEmpresaMapper;
    }

    @Override
    public List<TipoEmpresaDto> getTipoEmpresas() {
        return tipoEmpresaRepository.findAll()
                .stream()
                .map(tipoEmpresaMapper::toDto)
                .toList();
    }

    @Override
    public TipoEmpresaDto findTipoEmpresaById(Integer tipoEmpresaId) {
        return tipoEmpresaRepository.findById(tipoEmpresaId)
                .map(tipoEmpresaMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "TipoEmpresa con id " + tipoEmpresaId + " no existe"));
    }

    @Override
    public TipoEmpresaDto UpdateTipoEmpresa(TipoEmpresaDto tipoEmpresaDto) {
        if (tipoEmpresaDto.id() == null || !tipoEmpresaRepository.existsById(tipoEmpresaDto.id())) {
            throw new EntityNotFoundException(
                    "No se puede actualizar, TipoEmpresa con id " + tipoEmpresaDto.id() + " no existe");
        }
        TipoEmpresa entity = tipoEmpresaMapper.toEntity(tipoEmpresaDto);
        TipoEmpresa updated = tipoEmpresaRepository.save(entity);
        return tipoEmpresaMapper.toDto(updated);
    }

    @Override
    public void updateTipoEmpresa(TipoEmpresaDto tipoEmpresaDto) {
        if (tipoEmpresaDto.id() == null || !tipoEmpresaRepository.existsById(tipoEmpresaDto.id())) {
            throw new EntityNotFoundException(
                    "No se puede actualizar, TipoEmpresa con id " + tipoEmpresaDto.id() + " no existe");
        }
        TipoEmpresa entity = tipoEmpresaMapper.toEntity(tipoEmpresaDto);
        tipoEmpresaRepository.save(entity);
    }

    @Override
    public void deleteTipoEmpresa(Integer id) {
        if (tipoEmpresaRepository.existsById(id)) {
            tipoEmpresaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("TipoEmpresa con id " + id + " no existe");
        }
    }
}

