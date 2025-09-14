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
        Vacante vacante = vacanteRepository.findById(anuncioDto.vacanteId())
                .orElseThrow(() -> new EntityNotFoundException("Vacante con id " + anuncioDto.vacanteId() + " no encontrada"));

        Anuncio entity = new Anuncio();
        entity.setIdVacante(vacante);
        entity.setNombrePublicoAnuncio(anuncioDto.NombrePublicoAnuncio());
        entity.setNombrePrivadoAnuncio(anuncioDto.nombrePrivadoAnuncio());
        entity.setTipoAnuncio(anuncioDto.tipoAnuncio());
        entity.setTamanioAnuncio(anuncioDto.tamanioAnuncio());

        Anuncio saved = anuncioRepository.save(entity);

        return new AnuncioDto(
                saved.getId(),
                saved.getIdVacante().getId(),
                saved.getNombrePublicoAnuncio(),
                saved.getNombrePrivadoAnuncio(),
                saved.getTipoAnuncio(),
                saved.getTamanioAnuncio()
        );
    }

    @Override
    public AnuncioDto updateAnuncio(Integer id, AnuncioDto anuncioDto) {
        Anuncio existing = anuncioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Anuncio con id " + id + " no existe"));

        existing.setNombrePublicoAnuncio(anuncioDto.NombrePublicoAnuncio());
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
