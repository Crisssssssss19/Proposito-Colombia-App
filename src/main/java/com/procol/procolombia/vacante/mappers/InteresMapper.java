package com.procol.procolombia.vacante.mappers;



import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.vacante.dto.InteresDto;
import com.procol.procolombia.vacante.dto.InteresIdDto;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.Interes;

import com.procol.procolombia.vacante.entities.InteresId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface InteresMapper {

    InteresIdDto toDto(InteresId entity);
    InteresId toEntity(InteresIdDto dto);

    InteresDto toDto(Interes interes);

    Interes toEntity(InteresDto dto);

    Set<Interes> toListEntity(Set<InteresDto> interesDtos);
    Set<InteresDto> toListDto(Set<Interes> interes);

}
