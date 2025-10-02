package com.procol.procolombia.vacante.services;

import com.procol.procolombia.vacante.dto.AnuncioDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.net.MalformedURLException;


public interface AnuncioService {
    AnuncioDto createAnuncio(Integer idVacante, MultipartFile file);
    AnuncioDto updateAnuncio(Integer id, MultipartFile file);
    void deleteAnuncio(Integer id);
    AnuncioDto findById(Integer id);
    List<AnuncioDto> findAll();
    AnuncioDto findByVacanteId(Integer vacanteId);
    Resource verImagen(Integer idAnuncio);
}
