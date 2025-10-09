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

    @Mapping(source = "idEmpresa", target = "idEmpresa")
    @Mapping(source = "idUsuario", target = "idUsuario")
    InteresIdDto toDto(InteresId entity);
    @Mapping(source = "idEmpresa", target = "idEmpresa")
    @Mapping(source = "idUsuario", target = "idUsuario")
    InteresId toEntity(InteresIdDto dto);

    InteresDto toDto(Interes interes);

    Interes toEntity(InteresDto dto);

    Set<Interes> toListEntity(Set<InteresDto> interesDtos);
    Set<InteresDto> toListDto(Set<Interes> interes);

    default Empresa intToEmpresa(Integer id) {
        if (id == null) return null;
        Empresa empresa = new Empresa();
        empresa.setId(id);
        return empresa;
    }

    default Usuario intToUsuario(Integer id) {
        if (id == null) return null;
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }

    default Integer map(Empresa empresa) {
        return (empresa != null) ? empresa.getId() : null;
    }

    default Integer map(Usuario usuario) {
        return (usuario != null) ? usuario.getId() : null;
    }
}
