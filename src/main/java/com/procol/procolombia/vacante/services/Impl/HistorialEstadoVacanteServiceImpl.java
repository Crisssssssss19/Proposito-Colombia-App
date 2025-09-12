package com.procol.procolombia.vacante.services.Impl;


import com.procol.procolombia.vacante.services.HistorialEstadoVacanteService;
import com.procol.procolombia.vacante.dto.HistorialEstadoVacanteDto;
import com.procol.procolombia.vacante.entities.HistorialEstadoVacante;
import com.procol.procolombia.vacante.mappers.HistorialEstadoVacanteMapper;
import com.procol.procolombia.vacante.repositories.HistorialEstadoVacanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistorialEstadoVacanteServiceImpl implements HistorialEstadoVacanteService {
    private final HistorialEstadoVacanteRepository historialEstadoVacanteRepository;
    private final HistorialEstadoVacanteMapper historialEstadoVacanteMapper;

    public HistorialEstadoVacanteServiceImpl(HistorialEstadoVacanteRepository historialEstadoVacanteRepository, HistorialEstadoVacanteMapper historialEstadoVacanteMapper) {
        this.historialEstadoVacanteRepository = historialEstadoVacanteRepository;
        this.historialEstadoVacanteMapper = historialEstadoVacanteMapper;
    }

    @Override
    public void deleteHistorialEstadoVacante(HistorialEstadoVacanteDto historialEstadoVacanteDto) {
        if (historialEstadoVacanteDto.id() == null) {
            throw new IllegalArgumentException("El ID del historial no puede ser nulo para eliminar");
        }
        historialEstadoVacanteRepository.deleteById(historialEstadoVacanteDto.id());
    }

    @Override
    public HistorialEstadoVacanteDto findById(Integer id) {
        HistorialEstadoVacante historial = historialEstadoVacanteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HistorialEstadoVacante no encontrado con id: " + id));
        return historialEstadoVacanteMapper.toDto(historial);
    }

    @Override
    public List<HistorialEstadoVacanteDto> findByVacanteId(Integer vacanteId) {
        return historialEstadoVacanteRepository.findByIdVacante_Id(vacanteId)
                .stream()
                .map(historialEstadoVacanteMapper::toDto)
                .toList();
    }

}
