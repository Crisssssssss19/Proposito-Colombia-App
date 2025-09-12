package com.procol.procolombia.perfil.mappers;

import com.procol.procolombia.auth.entities.Imagen;
import com.procol.procolombia.perfil.dtos.request.SaveImagen;
import com.procol.procolombia.perfil.dtos.response.GetImagen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenMapper {
    // SaveImagen -> Imagen
    @Mapping(source = "idUsuario", target = "idUsuario.id")
    Imagen saveImagenToImagen(SaveImagen saveImagen);

    // Imagen -> GetImagen
    GetImagen imagenToGetImagen(Imagen imagen);

    List<GetImagen> imagenListToGetImagenList(List<Imagen> imagens);
}
