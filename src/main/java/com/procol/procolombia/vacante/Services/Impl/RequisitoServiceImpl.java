package com.procol.procolombia.vacante.Services.Impl;

import com.procol.procolombia.vacante.Services.RequisitoService;
import com.procol.procolombia.vacante.dto.RequisitoDto;
import com.procol.procolombia.vacante.entities.Requisito;
import com.procol.procolombia.vacante.mappers.RequisitoMapper;
import com.procol.procolombia.vacante.repositories.RequisitoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitoServiceImpl implements RequisitoService {
    private final RequisitoRepository requisitoRepository;
    private final RequisitoMapper requisitoMapper;

    public RequisitoServiceImpl(RequisitoRepository requisitoRepository,
                                RequisitoMapper requisitoMapper) {
        this.requisitoRepository = requisitoRepository;
        this.requisitoMapper = requisitoMapper;
    }

    @Override
    public RequisitoDto createRequisito(RequisitoDto requisitoDto) {
        Requisito requisito = requisitoMapper.toEntity(requisitoDto);
        Requisito saved = requisitoRepository.save(requisito);
        return requisitoMapper.toDto(saved);
    }

    @Override
    public RequisitoDto updateRequisito(RequisitoDto requisitoDto) {
        if (requisitoDto.id() == null || !requisitoRepository.existsById(requisitoDto.id())) {
            throw new EntityNotFoundException("El requisito con id " + requisitoDto.id() + " no existe");
        }
        Requisito requisito = requisitoMapper.toEntity(requisitoDto);
        Requisito updated = requisitoRepository.save(requisito);
        return requisitoMapper.toDto(updated);
    }

    @Override
    public void deleteRequisito(Integer id) {
        if (requisitoRepository.existsById(id)) {
            requisitoRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("El requisito con id " + id + " no existe");
        }
    }

    @Override
    public List<RequisitoDto> findAllRequisitos() {
        return requisitoRepository.findAll()
                .stream()
                .map(requisitoMapper::toDto)
                .toList();
    }

    @Override
    public List<RequisitoDto> getRequisitosByIdVacante(Integer idVacante) {
        return requisitoRepository.findByIdVacanteId(idVacante)
                .stream()
                .map(requisitoMapper::toDto)
                .toList();
    }
}
