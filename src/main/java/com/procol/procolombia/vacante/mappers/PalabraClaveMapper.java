package com.procol.procolombia.vacante.mappers;

import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.vacante.dto.PalabrasClaveDto;
import com.procol.procolombia.vacante.entities.PalabraClave;
import com.procol.procolombia.vacante.entities.Vacante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PalabraClaveMapper {

    @Mapping(target = "usuarioIds", source = "usuarios", qualifiedByName = "usuariosToIds")
    @Mapping(target = "vacanteIds", source = "vacantes", qualifiedByName = "vacantesToIds")
    PalabrasClaveDto toDto(PalabraClave entity);

    @Mapping(target = "usuarios", source = "usuarioIds", qualifiedByName = "idsToUsuarios")
    @Mapping(target = "vacantes", source = "vacanteIds", qualifiedByName = "idsToVacantes")
    PalabraClave toEntity(PalabrasClaveDto dto);

    @Named("usuariosToIds")
    default Set<Integer> mapUsuariosToIds(Set<Usuario> usuarios) {
        return usuarios == null ? null :
                usuarios.stream().map(Usuario::getId).collect(Collectors.toSet());
    }

    @Named("vacantesToIds")
    default Set<Integer> mapVacantesToIds(Set<Vacante> vacantes) {
        return vacantes == null ? null :
                vacantes.stream().map(Vacante::getId).collect(Collectors.toSet());
    }

    @Named("idsToUsuarios")
    default Set<Usuario> mapIdsToUsuarios(Set<Integer> ids) {
        return ids == null ? null :
                ids.stream().map(id -> {
                    Usuario usuario = new Usuario();
                    usuario.setId(id);
                    return usuario;
                }).collect(Collectors.toSet());
    }

    @Named("idsToVacantes")
    default Set<Vacante> mapIdsToVacantes(Set<Integer> ids) {
        return ids == null ? null :
                ids.stream().map(id -> {
                    Vacante vacante = new Vacante();
                    vacante.setId(id);
                    return vacante;
                }).collect(Collectors.toSet());
    }
}
