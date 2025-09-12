package com.procol.procolombia.perfil.mappers;

import com.procol.procolombia.perfil.dtos.request.SaveArchivo;
import com.procol.procolombia.perfil.dtos.response.GetArchivo;
import com.procol.procolombia.postulacion.entities.Archivo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArchivoMapper {
    // SaveArchivo -> Archivo
    @Mapping(source = "idUsuario", target = "idUsuario.id")
    Archivo saveArchivoToArchivo(SaveArchivo saveArchivo);

    // Archivo -> GetArchivo
    GetArchivo archivoToGetArchivo(Archivo archivo);

    List<GetArchivo> archivosToGetArchivoList(List<Archivo> archivos);
}
