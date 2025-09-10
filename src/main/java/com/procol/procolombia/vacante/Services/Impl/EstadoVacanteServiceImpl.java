package com.procol.procolombia.vacante.Services.Impl;

import com.procol.procolombia.vacante.Services.EstadoVacanteService;
import com.procol.procolombia.vacante.dto.EstadosVacanteDto;
import com.procol.procolombia.vacante.entities.EstadoVacante;
import com.procol.procolombia.vacante.mappers.EstadoVacanteMapper;
import com.procol.procolombia.vacante.repositories.EstadoVacanteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoVacanteServiceImpl implements EstadoVacanteService {

    private final EstadoVacanteRepository estadoVacanteRepository;
    private final EstadoVacanteMapper estadoVacanteMapper;

    public EstadoVacanteServiceImpl(EstadoVacanteRepository estadoVacanteRepository,
                                    EstadoVacanteMapper estadoVacanteMapper) {
        this.estadoVacanteRepository = estadoVacanteRepository;
        this.estadoVacanteMapper = estadoVacanteMapper;
    }

    @Override
    public void deleteByEstadoVacanteId(Integer vacanteId) {
        estadoVacanteRepository.deleteById(vacanteId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadosVacanteDto> getAllEstadosVacante() {
        return estadoVacanteRepository.findAll()
                .stream()
                .map(estadoVacanteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadosVacanteDto> getAllEstadosVacanteById(Integer vacanteId) {
        return estadoVacanteRepository.findByEstadoVacantesId(vacanteId)
                .stream()
                .map(estadoVacanteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EstadosVacanteDto updateEstadoVacante(EstadosVacanteDto estadosVacanteDto) {
        EstadoVacante entity = estadoVacanteMapper.toEntity(estadosVacanteDto);
        entity = estadoVacanteRepository.save(entity);
        return estadoVacanteMapper.toDto(entity);
    }
}
