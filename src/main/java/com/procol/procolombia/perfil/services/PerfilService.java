package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.response.GetPerfil;

public interface PerfilService {
    GetPerfil obtenerPerfilCompleto(Integer idUsuario);
}
