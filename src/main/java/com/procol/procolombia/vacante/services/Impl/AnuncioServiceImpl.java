package com.procol.procolombia.vacante.services.Impl;

import com.procol.procolombia.vacante.services.AnuncioService;
import com.procol.procolombia.vacante.dto.AnuncioDto;
import com.procol.procolombia.vacante.entities.Anuncio;
import com.procol.procolombia.vacante.entities.Vacante;
import com.procol.procolombia.vacante.mappers.AnuncioMapper;
import com.procol.procolombia.vacante.repositories.AnuncioRepository;
import com.procol.procolombia.vacante.repositories.VacanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnuncioServiceImpl implements AnuncioService {
    private final AnuncioRepository anuncioRepository;
    private final VacanteRepository vacanteRepository;
    private final AnuncioMapper anuncioMapper;

    public AnuncioServiceImpl(AnuncioRepository anuncioRepository,
                              VacanteRepository vacanteRepository,
                              AnuncioMapper anuncioMapper) {
        this.anuncioRepository = anuncioRepository;
        this.vacanteRepository = vacanteRepository;
        this.anuncioMapper = anuncioMapper;
    }

    @Override
    public AnuncioDto createAnuncio(AnuncioDto anuncioDto) {
            Vacante vacante = vacanteRepository.findById(anuncioDto.vacantesId())
                    .orElseThrow(() -> new EntityNotFoundException("Vacante con id " + anuncioDto.vacantesId() + " no encontrada"));

            Anuncio entity = anuncioMapper.toEntity(anuncioDto);
            Anuncio saved = anuncioRepository.save(entity);
            return anuncioMapper.toDto(saved);
    }

    @Override
    public AnuncioDto updateAnuncio(Integer id, AnuncioDto anuncioDto) {
        Anuncio existing = anuncioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Anuncio con id " + id + " no existe"));

        existing.setNombrePublicoAnuncio(anuncioDto.nombrePublicoAnuncio());
        existing.setNombrePrivadoAnuncio(anuncioDto.nombrePrivadoAnuncio());
        existing.setTipoAnuncio(anuncioDto.tipoAnuncio());
        existing.setTamanioAnuncio(anuncioDto.tamanioAnuncio());

        Anuncio updated = anuncioRepository.save(existing);
        return anuncioMapper.toDto(updated);
    }

    @Override
    public void deleteAnuncio(Integer id) {
        if (!anuncioRepository.existsById(id)) {
            throw new EntityNotFoundException("Anuncio con id " + id + " no existe");
        }
        anuncioRepository.deleteById(id);
    }

    @Override
    public AnuncioDto findById(Integer id) {
        return anuncioRepository.findById(id)
                .map(anuncioMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Anuncio con id " + id + " no existe"));
    }

    @Override
    public List<AnuncioDto> findAll() {
        return anuncioRepository.findAll()
                .stream()
                .map(anuncioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnuncioDto findByVacanteId(Integer vacanteId) {
        return anuncioRepository.findByIdVacante_Id(vacanteId)
                .map(anuncioMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Anuncio con vacanteId " + vacanteId + " no existe"));
    }
}
