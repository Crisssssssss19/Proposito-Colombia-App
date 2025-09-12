package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.request.SaveArchivo;
import com.procol.procolombia.perfil.dtos.response.GetArchivo;

import java.util.List;

public interface ArchivoService {
    GetArchivo crearArchivo(SaveArchivo  saveArchivo);
    List<GetArchivo> listarArchivos(Integer idUsuario);
    void eliminarArchivo(Integer idArchivo);
}
