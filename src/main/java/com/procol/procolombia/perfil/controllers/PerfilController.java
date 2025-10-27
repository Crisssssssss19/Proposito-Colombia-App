package com.procol.procolombia.perfil.controllers;


import com.procol.procolombia.perfil.dtos.response.*;
import com.procol.procolombia.perfil.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/{id}/completo")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASPIRANTE', 'EMPRESA')")
    public ResponseEntity<ApiResponse<GetPerfil>> obtnenerPerfilCompleto(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Perfil obtenido correctamente", perfilService.obtenerPerfilCompleto(id), HttpStatus.OK));
    }

    @PutMapping("/{id}/palabras-clave")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASPIRANTE', 'EMPRESA')")
    public ResponseEntity<ApiResponse<Void>> actualizarPalabrasClave(
            @PathVariable Integer id,
            @RequestBody List<String> palabrasClave
    ) {
        perfilService.actualizarPalabrasClave(id, palabrasClave);
        return ResponseEntity.ok(ApiResponse.success("Palabras clave actualizadas correctamente", null, HttpStatus.OK));
    }
}
