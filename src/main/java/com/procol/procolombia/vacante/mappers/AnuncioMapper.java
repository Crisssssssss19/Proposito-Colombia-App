package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.AnuncioDto;
import com.procol.procolombia.vacante.entities.Anuncio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnuncioMapper {

    AnuncioDto toDto(Anuncio anuncio);

    Anuncio toEntity(AnuncioDto dto);

}