package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.AnuncioDto;

import java.util.List;

public interface AnuncioService {
    AnuncioDto createAnuncio(AnuncioDto anuncioDto);
    AnuncioDto updateAnuncio(Integer id, AnuncioDto anuncioDto);
    void deleteAnuncio(Integer id);
    AnuncioDto findById(Integer id);
    List<AnuncioDto> findAll();
    AnuncioDto findByVacanteId(Integer vacanteId);
}
