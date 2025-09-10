package com.procol.procolombia.vacante.mappers;



import com.procol.procolombia.vacante.dto.InteresDto;
import com.procol.procolombia.vacante.entities.Interes;

import java.util.Set;

public interface InteresMapper {
    Interes toEntity(InteresDto interesDto );
    InteresDto toDto(Interes interes );
    Set<Interes> toListEntity(Set<InteresDto> interesDtos);
    Set<InteresDto> toListDto(Set<Interes> relUsuarioEmpresas);
}
