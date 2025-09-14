package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.services.TipoEmpresaService;
import com.procol.procolombia.vacante.dto.TipoEmpresaDto;
import com.procol.procolombia.vacante.entities.TipoEmpresa;
import com.procol.procolombia.vacante.mappers.EmpresaMapper;
import com.procol.procolombia.vacante.mappers.TipoEmpresaMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.procol.procolombia.vacante.repositories.TipoEmpresaRepository;

@Service
public class TipoEmpresaServiceImpl implements TipoEmpresaService {

    private final TipoEmpresaRepository tipoEmpresaRepository;
    private final TipoEmpresaMapper tipoEmpresaMapper;

    public TipoEmpresaServiceImpl(TipoEmpresaRepository tipoEmpresaRepository,
                                  TipoEmpresaMapper tipoEmpresaMapper, EmpresaMapper empresaMapper) {
        this.tipoEmpresaRepository = tipoEmpresaRepository;
        this.tipoEmpresaMapper = tipoEmpresaMapper;
    }


    @Override
    public TipoEmpresaDto findTipoEmpresaById(Integer tipoEmpresaId) {
        return tipoEmpresaRepository.findById(tipoEmpresaId)
                .map(tipoEmpresaMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "TipoEmpresa con id " + tipoEmpresaId + " no existe"));
    }

    @Override
    public TipoEmpresaDto updateTipoEmpresa(Integer id, TipoEmpresaDto tipoEmpresaDto) {
        if (id == null || !tipoEmpresaRepository.existsById(id)){
            throw new EntityNotFoundException(
                    "No se puede actualizar, TipoEmpresa con id " + id + " no existe");
        }
        TipoEmpresa entity = tipoEmpresaMapper.toEntity(tipoEmpresaDto);
        TipoEmpresa updated = tipoEmpresaRepository.save(entity);
        return tipoEmpresaMapper.toDto(updated);
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

