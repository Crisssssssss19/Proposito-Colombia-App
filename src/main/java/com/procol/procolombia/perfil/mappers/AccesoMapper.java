package com.procol.procolombia.perfil.mappers;

import com.procol.procolombia.auth.entities.Acceso;
import com.procol.procolombia.perfil.dtos.request.SaveAcceso;
import com.procol.procolombia.perfil.dtos.response.GetAcceso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccesoMapper {

    // SaveAcceso -> Acceso
    @Mapping(source = "idUsuario", target = "usuario.id")
    Acceso saveAccesoToAcceso(SaveAcceso saveAcceso);

    // Acceso -> GetAcceso
    @Mapping(source = "usuario.id", target = "idUsuario")
    GetAcceso accesoToGetAcceso(Acceso acceso);

    List<GetAcceso> accesosToGetAccesos(List<Acceso> accesos);
}
