package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.JornadaDto;
import com.procol.procolombia.vacante.entities.Jornada;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JornadaMapper {
    JornadaDto toDto(Jornada entity);
    Jornada toEntity(JornadaDto dto);
}
