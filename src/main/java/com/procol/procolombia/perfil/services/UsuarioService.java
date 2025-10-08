package com.procol.procolombia.perfil.services;

import com.procol.procolombia.perfil.dtos.request.SaveUsuario;
import com.procol.procolombia.perfil.dtos.response.GetUsuario;

import java.util.List;

public interface UsuarioService {

    GetUsuario crearUsuario(SaveUsuario saveUsuario);

    GetUsuario actualizarUsuario(Integer id, SaveUsuario saveUsuario);

    GetUsuario obtenerUsuarioPorId(Integer id);

    List<GetUsuario> listarUsuarios();

    void eliminarUsuario(Integer id);
}
