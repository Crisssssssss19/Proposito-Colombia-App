package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.services.EstadoVacanteService;
import com.procol.procolombia.vacante.dto.EstadosVacanteDto;
import com.procol.procolombia.vacante.entities.EstadoVacante;
import com.procol.procolombia.vacante.mappers.EstadoVacanteMapper;
import com.procol.procolombia.vacante.repositories.EstadoVacanteRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public EstadosVacanteDto updateEstadoVacante(EstadosVacanteDto estadosVacanteDto) {
        EstadoVacante entity = estadoVacanteMapper.toEntity(estadosVacanteDto);
        entity = estadoVacanteRepository.save(entity);
        return estadoVacanteMapper.toDto(entity);
    }

    @Override
    public EstadosVacanteDto findById(Integer id) {
        return estadoVacanteRepository.findById(id)
                .map(estadoVacanteMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "EstadoVacante con id " + id + " no existe"));
    }
}
