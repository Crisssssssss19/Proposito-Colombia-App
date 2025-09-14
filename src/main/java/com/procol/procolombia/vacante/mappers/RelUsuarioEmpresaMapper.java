package com.procol.procolombia.vacante.mappers;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.vacante.dto.RelUsuarioEmpresaDto;
import com.procol.procolombia.vacante.entities.Empresa;
import com.procol.procolombia.vacante.entities.RelUsuarioEmpresa;
import com.procol.procolombia.vacante.entities.Vacante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RelUsuarioEmpresaMapper {

    @Mapping(source = "idUsuario.id", target = "idUsuario")
    @Mapping(source = "idEmpresa.id", target = "idEmpresa")
    RelUsuarioEmpresaDto toDto(RelUsuarioEmpresa entity);

    @Mapping(source = "idUsuario", target = "idUsuario", qualifiedByName = "mapUsuario")
    @Mapping(source = "idEmpresa", target = "idEmpresa", qualifiedByName = "mapEmpresa")
    @Mapping(target = "vacantes", ignore = true)
    RelUsuarioEmpresa toEntity(RelUsuarioEmpresaDto dto);

    Set<RelUsuarioEmpresa> toListEntity(Set<RelUsuarioEmpresaDto> dtos);
    Set<RelUsuarioEmpresaDto> toListDto(Set<RelUsuarioEmpresa> entities);


    @Named("mapUsuario")
    default Usuario mapUsuario(Integer id) {
        if (id == null) return null;
        Usuario u = new Usuario();
        u.setId(id);
        return u;
    }

    @Named("mapEmpresa")
    default Empresa mapEmpresa(Integer id) {
        if (id == null) return null;
        Empresa e = new Empresa();
        e.setId(id);
        return e;
    }
}
