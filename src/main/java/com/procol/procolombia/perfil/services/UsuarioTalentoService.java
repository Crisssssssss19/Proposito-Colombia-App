package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.request.SaveUsuarioTalento;
import com.procol.procolombia.perfil.dtos.response.GetUsuarioTalento;

import java.util.List;

public interface UsuarioTalentoService {
    // Metodo para asignar talentos a un usuario
    GetUsuarioTalento agregarTalento(Integer idUsuario, SaveUsuarioTalento saveUsuarioTalento);

    List<GetUsuarioTalento> listarTalentosPorUsuario(Integer idUsuario);

    GetUsuarioTalento actualizarNivelDominio(Integer idUsuario, Integer idUsuarioTalento, SaveUsuarioTalento saveUsuarioTalento);

    void eliminarUsuarioTalento(Integer idUsuarioTalento);
}
