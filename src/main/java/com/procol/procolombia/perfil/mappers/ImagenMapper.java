package com.procol.procolombia.perfil.mappers;

import com.procol.procolombia.auth.entities.Imagene;
import com.procol.procolombia.perfil.dtos.request.SaveImagen;
import com.procol.procolombia.perfil.dtos.response.GetImagen;
import com.procol.procolombia.perfil.dtos.response.GetImagenConUrl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

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

    @Mapping(source = "nombrePublicoImagen", target = "nombrePublico")
    @Mapping(
            target = "url",
            expression = "java(\"http://localhost:3210/usuarios/\" + imagene.getIdUsuario().getId() + \"/imagenes/ver/\" + imagene.getNombrePrivadoImagen())"
    )
    @Mapping(
            target = "favorita",
            expression = "java(imagene.getFavoritaImagen() == 1)"
    )
    @Mapping(source = "tipoImagen", target = "tipo")
    @Mapping(source = "tamanioImagen", target = "tamanio")
    @Mapping(source = "fechaSubida", target = "fechaSubida")
    GetImagenConUrl imagenToGetImagenConUrl(Imagene imagene);

    default List<GetImagenConUrl> imagenListToGetImagenConUrlList(List<Imagene> imagenes) {
        if (imagenes == null) return null;
        return imagenes.stream()
                .map(this::imagenToGetImagenConUrl)
                .collect(Collectors.toList());
    }
}
