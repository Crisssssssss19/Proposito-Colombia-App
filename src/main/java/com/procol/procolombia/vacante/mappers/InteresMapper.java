package com.procol.procolombia.vacante.mappers;



import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.vacante.dto.InteresDto;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.Interes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface InteresMapper {
    @Mapping(source = "idEmpresa", target = "idEmpresa", qualifiedByName = "mapEmpresa")
    @Mapping(source = "idUsuario", target = "idUsuario", qualifiedByName = "mapUsuario")
    Interes toEntity(InteresDto interesDto);

    @Mapping(source = "idEmpresa.id", target = "idEmpresa")
    @Mapping(source = "idUsuario.id", target = "idUsuario")
    InteresDto toDto(Interes interes);

    Set<Interes> toListEntity(Set<InteresDto> interesDtos);
    Set<InteresDto> toListDto(Set<Interes> interes);

    // Métodos auxiliares

    @Named("mapEmpresa")
    default Empresa mapEmpresa(Integer id) {
        if (id == null) return null;
        Empresa e = new Empresa();
        e.setId(id);
        return e;
    }

    @Named("mapUsuario")
    default Usuario mapUsuario(Integer id) {
        if (id == null) return null;
        Usuario u = new Usuario();
        u.setId(id);
        return u;
    }
}
