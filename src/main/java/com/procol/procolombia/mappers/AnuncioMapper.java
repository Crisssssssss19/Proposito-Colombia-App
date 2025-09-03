package com.procol.procolombia.mappers;

import com.procol.procolombia.entities.Anuncio;
import com.procol.procolombia.entities.dto.AnuncioDto;

import java.util.List;
import java.util.stream.Collectors;

public class AnuncioMapper {

    public static AnuncioDto toDto(Anuncio anuncio) {
        if (anuncio == null) return null;
        AnuncioDto dto = new AnuncioDto();
        dto.setId(anuncio.getId());
        dto.setNombrePublicoAnuncio(anuncio.getNombrePublicoAnuncio());
        dto.setNombrePrivadoAnuncio(anuncio.getNombrePrivadoAnuncio());
        dto.setTipoAnuncio(anuncio.getTipoAnuncio());
        dto.setTamanioAnuncio(anuncio.getTamanioAnuncio());
        return dto;
    }

    public static Anuncio toEntity(AnuncioDto dto) {
        if (dto == null) return null;
        Anuncio anuncio = new Anuncio();
        anuncio.setId(dto.getId());
        anuncio.setNombrePublicoAnuncio(dto.getNombrePublicoAnuncio());
        anuncio.setNombrePrivadoAnuncio(dto.getNombrePrivadoAnuncio());
        anuncio.setTipoAnuncio(dto.getTipoAnuncio());
        anuncio.setTamanioAnuncio(dto.getTamanioAnuncio());
        return anuncio;
    }

    public static List<AnuncioDto> toDtoList(List<Anuncio> anuncios) {
        return anuncios == null ? null : anuncios.stream().map(AnuncioMapper::toDto).collect(Collectors.toList());
    }

    public static List<Anuncio> toEntityList(List<AnuncioDto> dtos) {
        return dtos == null ? null : dtos.stream().map(AnuncioMapper::toEntity).collect(Collectors.toList());
    }
}