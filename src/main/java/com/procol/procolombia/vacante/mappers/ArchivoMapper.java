package com.procol.procolombia.mappers;

import com.procol.procolombia.entities.Archivo;
import com.procol.procolombia.entities.dto.ArchivoDto;

import java.util.List;
import java.util.stream.Collectors;

public class ArchivoMapper {

    public static ArchivoDto toDto(Archivo archivo) {
        if (archivo == null) return null;
        ArchivoDto archivoDto = new ArchivoDto();
        archivoDto.setId(archivo.getId());
        archivoDto.setNombrePublicoArchivo(archivo.getNombrePublicoArchivo());
        archivoDto.setNombreArchivoArchivo(archivo.getNombreArchivoArchivo());
        archivoDto.setTipoArchivo(archivo.getTipoArchivo());
        archivoDto.setTamanioArchivo(archivo.getTamanioArchivo());
        archivoDto.setGrupoArchivo(archivo.getGrupoArchivo());
        return archivoDto;
    }

    public static Archivo toEntity(ArchivoDto archivoDto) {
        if (archivoDto == null) return null;
        Archivo archivo = new Archivo();
        archivo.setId(archivoDto.getId());
        archivo.setNombrePublicoArchivo(archivoDto.getNombrePublicoArchivo());
        archivo.setNombreArchivoArchivo(archivoDto.getNombreArchivoArchivo());
        archivo.setTipoArchivo(archivoDto.getTipoArchivo());
        archivo.setTamanioArchivo(archivoDto.getTamanioArchivo());
        archivo.setGrupoArchivo(archivoDto.getGrupoArchivo());
        return archivo;
    }

    public static List<ArchivoDto> toDtoList(List<Archivo> archivos) {
        return archivos == null ? null : archivos.stream().map(ArchivoMapper::toDto).collect(Collectors.toList());
    }
    public static List<Archivo> toEntityList(List<ArchivoDto> archivosDto) {
        return archivosDto == null ? null : archivosDto.stream().map(ArchivoMapper::toEntity).collect(Collectors.toList());
    }
}
