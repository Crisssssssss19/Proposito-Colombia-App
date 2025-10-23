package com.procol.procolombia.perfil.mappers;

import com.procol.procolombia.auth.entities.Imagene;
import com.procol.procolombia.perfil.dtos.request.SaveImagen;
import com.procol.procolombia.perfil.dtos.response.GetImagen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ImagenMapper {
    // SaveImagen -> Imagene
    @Mapping(source = "idUsuario", target = "idUsuario.id")
    Imagene saveImagenToImagen(SaveImagen saveImagen);

    // Imagene -> GetImagen
    @Mapping(source = "nombrePublicoImagen", target = "nombrePublico")
    @Mapping(source = "nombrePrivadoImagen", target = "nombrePrivado")
    @Mapping(source = "favoritaImagen", target = "favorita")
    @Mapping(source = "tipoImagen", target = "tipo")
    @Mapping(source = "tamanioImagen", target = "tamanio")
    @Mapping(source = "fechaSubida", target = "fechaSubida")
    GetImagen imagenToGetImagen(Imagene imagene);

    List<GetImagen> imagenListToGetImagenList(List<Imagene> imagenes);
}
