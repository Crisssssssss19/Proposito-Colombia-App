package com.procol.procolombia.perfil.mappers;
import com.procol.procolombia.auth.entities.Usuario;
import com.procol.procolombia.perfil.dtos.request.SaveUsuario;
import com.procol.procolombia.perfil.dtos.response.GetUsuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ImagenMapper.class, ArchivoMapper.class})
public interface UsuarioMapper {
    // SaveUsuario -> Usuario
    @Mapping(source = "idUbicacion", target = "idUbicacion.id")
    @Mapping(target = "talentos", ignore = true) // Ignorar el mapeo de talentos
    Usuario saveUsuarioToUsuario(SaveUsuario saveUsuario);

    // Usuario -> GetUsuario
    @Mapping(source = "nombresUsuario", target = "nombresUsuario")
    @Mapping(source = "apellidosUsuario", target = "apellidosUsuario")
    @Mapping(source = "estadoUsuario", target = "estado")
    @Mapping(source = "idUbicacion.id", target = "idUbicacion")
    GetUsuario usuarioToGetUsuario(Usuario usuario);

    List<GetUsuario> usuarioListToGetUsuarioList(List<Usuario> usuarioList);
}
