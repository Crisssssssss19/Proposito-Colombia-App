package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.HistorialEstadoVacanteDto;
import com.procol.procolombia.vacante.entities.EstadoVacante;
import com.procol.procolombia.vacante.entities.HistorialEstadoVacante;
import com.procol.procolombia.vacante.entities.Vacante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface HistorialEstadoVacanteMapper {


    @Mapping(source = "idVacante.id", target = "idVacante")
    @Mapping(source = "idEstadoVacante.id", target = "idEstadoVacante")
    HistorialEstadoVacanteDto toDto(HistorialEstadoVacante entity);

    @Mapping(source = "idVacante", target = "idVacante", qualifiedByName = "mapVacante")
    @Mapping(source = "idEstadoVacante", target = "idEstadoVacante", qualifiedByName = "mapEstadoVacante")
    HistorialEstadoVacante toEntity(HistorialEstadoVacanteDto dto);

    @Named("mapVacante")
    default Vacante mapVacante(Integer id) {
        if (id == null) return null;
        Vacante vacante = new Vacante();
        vacante.setId(id);
        return vacante;
    }

    @Named("mapEstadoVacante")
    default EstadoVacante mapEstadoVacante(Integer id) {
        if (id == null) return null;
        EstadoVacante estado = new EstadoVacante();
        estado.setId(id);
        return estado;
    }
}
