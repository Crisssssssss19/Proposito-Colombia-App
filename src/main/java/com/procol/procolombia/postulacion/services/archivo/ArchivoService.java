package com.procol.procolombia.postulacion.services.archivo;

import com.procol.procolombia.postulacion.dto.ArchivoDto;

import java.util.List;

public interface ArchivoService {
    List<ArchivoDto> findByUsuario(Integer idUsuario);
    List<ArchivoDto> findByGrupoArchivo(Short grupoArchivo);
    List<ArchivoDto> findByTipoArchivo(String tipoArchivo);
    List<ArchivoDto> findByUsuarioAndGrupo(Integer usuarioId, Short grupo);
    long countByUsuario(Integer usuarioId);
    List<ArchivoDto> searchByNombre(String nombre);
}
