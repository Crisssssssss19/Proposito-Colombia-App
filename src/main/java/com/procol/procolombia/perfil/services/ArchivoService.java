package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.request.SaveArchivoFile;
import com.procol.procolombia.perfil.dtos.response.GetArchivo;
import org.springframework.core.io.Resource;

import java.util.List;

public interface ArchivoService {
    GetArchivo SubirArchivo(Integer idUsuario, SaveArchivoFile saveArchivo);
    List<GetArchivo> listarArchivosPorUsuario(Integer idUsuario);
    void eliminarArchivo(Integer idArchivo);
    GetArchivo obtenerArchivoPorId(Integer idArchivo);
    Resource descargarArchivo(Integer idArchivo, boolean inline);
}
