package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.ModalidadDto;
import com.procol.procolombia.vacante.entities.Modalidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModalidadMapper {
    @Mapping(target = "vacantes", ignore = true)
    Modalidad toModalidad(ModalidadDto modalidadDto);
    ModalidadDto toModalidadDto(Modalidad modalidad);

    List<ModalidadDto> toModalidadeDtos(List<Modalidad> modalidades);
}
