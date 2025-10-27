package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.response.GetPerfil;

import java.util.List;

public interface PerfilService {
    GetPerfil obtenerPerfilCompleto(Integer idUsuario);

    void actualizarPalabrasClave(Integer idUsuario, List<String> palabrasClave);
}
