package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.request.SaveAcceso;
import com.procol.procolombia.perfil.dtos.response.GetAcceso;

import java.util.List;

public interface AccesoService {

    GetAcceso crearAcceso(SaveAcceso saveAcceso);

    GetAcceso actualizarAcceso(SaveAcceso saveAcceso, Integer idUsuario);

    GetAcceso obtenerAccesoPorUsuarioId(Integer idUsuario);

    List<GetAcceso> lisatarAccesos();

    void eliminarAcceso(Integer idUsuario);
}
