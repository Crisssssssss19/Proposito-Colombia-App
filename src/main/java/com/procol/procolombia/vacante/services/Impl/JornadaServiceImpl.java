package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.dto.JornadaDto;
import com.procol.procolombia.vacante.entities.Jornada;
import com.procol.procolombia.vacante.mappers.JornadaMapper;
import com.procol.procolombia.vacante.repositories.JornadaRepository;
import com.procol.procolombia.vacante.services.JornadaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JornadaServiceImpl implements JornadaService {
    private final JornadaRepository jornadaRepository;
    private final JornadaMapper jornadaMapper;

    public JornadaServiceImpl(JornadaRepository jornadaRepository,  JornadaMapper jornadaMapper) {
        this.jornadaRepository = jornadaRepository;
        this.jornadaMapper = jornadaMapper;
    }

    @Override
    public JornadaDto createJornada(JornadaDto jornadaDto){
        Jornada jornada = jornadaMapper.toEntity(jornadaDto);
        Jornada saved = jornadaRepository.save(jornada);
        return jornadaMapper.toDto(saved);
    }

    @Override
    public JornadaDto updateJornada(JornadaDto jornadaDto){
        if (jornadaDto.id() == null || !jornadaRepository.existsById(jornadaDto.id())) {
            throw new EntityNotFoundException("La jornada con id " + jornadaDto.id() + " no existe");
        }
        Jornada jornada = jornadaMapper.toEntity(jornadaDto);
        Jornada updated = jornadaRepository.save(jornada);
        return jornadaMapper.toDto(updated);
    }

    @Override
    public JornadaDto getJornadaById(int id) {
        return jornadaMapper.toDto(jornadaRepository.getReferenceById(id));
    }

    @Override
    public void deleteJornada(Integer id){
        if (jornadaRepository.existsById(id)) {
            jornadaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("La jornada con id " + id + " no existe");
        }
    }
}
