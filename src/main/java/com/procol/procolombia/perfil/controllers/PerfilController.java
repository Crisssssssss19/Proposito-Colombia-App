package com.procol.procolombia.perfil.controllers;


import com.procol.procolombia.perfil.dtos.response.*;
import com.procol.procolombia.perfil.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/{id}/completo")
    public ResponseEntity<ApiResponse<GetPerfil>> obtnenerPerfilCompleto(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Perfil obtenido correctamente", perfilService.obtenerPerfilCompleto(id), HttpStatus.OK));
    }
}
