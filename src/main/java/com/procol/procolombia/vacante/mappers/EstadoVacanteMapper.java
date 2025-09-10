package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.EstadosVacanteDto;
import com.procol.procolombia.vacante.entities.EstadoVacante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface EstadoVacanteMapper {
    EstadoVacante toEntity(EstadosVacanteDto dto);
    EstadosVacanteDto toDto(EstadoVacante entity);
}
