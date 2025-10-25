package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveUsuarioTalento;
import com.procol.procolombia.perfil.dtos.response.ApiResponse;
import com.procol.procolombia.perfil.dtos.response.GetUsuarioTalento;
import com.procol.procolombia.perfil.services.UsuarioTalentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios_talentos")
public class UsuarioTalentoController {

    private final UsuarioTalentoService usuarioTalentoService;

    public UsuarioTalentoController(UsuarioTalentoService usuarioTalentoService) {
        this.usuarioTalentoService = usuarioTalentoService;
    }

    @PostMapping("/{idUsuario}")
    @PreAuthorize("hasAuthority('ASPIRANTE')")
    public ResponseEntity<ApiResponse<Void>> agregarTalento(@PathVariable Integer idUsuario, @RequestBody SaveUsuarioTalento getTalento) {
        usuarioTalentoService.agregarTalento(idUsuario, getTalento);
        return ResponseEntity.ok(ApiResponse.success("Talento agregado exitosamente", null, HttpStatus.CREATED));
    }

    @GetMapping("/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('ASPIRANTE')")
    public ResponseEntity<ApiResponse<List<GetUsuarioTalento>>> listarTalentosPorUsuario(@PathVariable Integer idUsuario) {
        List<GetUsuarioTalento> talentos = usuarioTalentoService.listarTalentosPorUsuario(idUsuario);
        return ResponseEntity.ok(ApiResponse.success("Lista de talentos del usuario obtenida correctamente", talentos, HttpStatus.OK));
    }

    @PutMapping("/{idUsuario}/{idUsuarioTalento}")
    public ResponseEntity<ApiResponse<GetUsuarioTalento>> actualizarNivelDominio(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idUsuarioTalento,
            @RequestBody SaveUsuarioTalento updateDto
    ) {
        GetUsuarioTalento actualizado = usuarioTalentoService.actualizarNivelDominio(idUsuario, idUsuarioTalento, updateDto);
        return ResponseEntity.ok(ApiResponse.success("Nivel de dominio actualizado exitosamente", actualizado, HttpStatus.OK));
    }

    @DeleteMapping("/{idUsuario}/{idUsuarioTalento}")
    @PreAuthorize("hasAuthority('ASPIRANTE')")
    public ResponseEntity<ApiResponse<Void>> eliminarUsuarioTalento(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idUsuarioTalento
    ) {
        usuarioTalentoService.eliminarUsuarioTalento(idUsuarioTalento);
        return ResponseEntity.ok(ApiResponse.success("Talento del usuario eliminado exitosamente", null, HttpStatus.OK));
    }
}
