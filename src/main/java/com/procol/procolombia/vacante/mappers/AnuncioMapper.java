package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.AnuncioDto;
import com.procol.procolombia.vacante.entities.Anuncio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnuncioMapper {

    @Mapping(source = "idVacante.id", target = "vacantesId")
    AnuncioDto toDto(Anuncio anuncio);

    @Mapping(source = "vacantesId", target = "idVacante.id")
    Anuncio toEntity(AnuncioDto dto);

}