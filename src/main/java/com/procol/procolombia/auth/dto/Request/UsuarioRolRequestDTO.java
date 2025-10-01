package com.procol.procolombia.auth.dto.Request;

import java.util.List;

public record UsuarioRolRequestDTO(
        Integer idUsuario,
        List<Integer> idRol
) {}
