package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.RequisitoDto;
import com.procol.procolombia.vacante.entities.Requisito;
import com.procol.procolombia.vacante.entities.Vacante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RequisitoMapper {
    @Mapping(source = "idVacante.id", target = "idVacante")
    RequisitoDto toDto(Requisito entity);

    @Mapping(source = "idVacante", target = "idVacante", qualifiedByName = "mapVacante")
    Requisito toEntity(RequisitoDto dto);

    @Named("mapVacante")
    default Vacante mapVacante(Integer id) {
        if (id == null) return null;
        Vacante vacante = new Vacante();
        vacante.setId(id);
        return vacante;
    }
}
