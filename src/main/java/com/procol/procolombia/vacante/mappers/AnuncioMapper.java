package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.vacante.dto.AnuncioDto;
import com.procol.procolombia.vacante.entities.Anuncio;
import com.procol.procolombia.vacante.entities.Vacante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AnuncioMapper {

    @Mapping(source = "idVacante.id", target = "idVacante")
    AnuncioDto toDto(Anuncio anuncio);

    Anuncio toEntity(AnuncioDto dto);

    default Vacante map(Integer id) {
        if (id == null) return null;
        Vacante v = new Vacante();
        v.setId(id);
        return v;
    }
}