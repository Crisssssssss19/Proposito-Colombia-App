package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.EstadosVacanteDto;
import com.procol.procolombia.vacante.entities.EstadoVacante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface EstadoVacanteMapper {
    @Mapping(target = "historialEstadosVacantes", ignore = true)
    EstadoVacante toEntity(EstadosVacanteDto dto);
    EstadosVacanteDto toDto(EstadoVacante entity);
}
