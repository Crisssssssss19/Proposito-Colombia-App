package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveUsuario;
import com.procol.procolombia.perfil.dtos.response.ApiResponse;
import com.procol.procolombia.perfil.dtos.response.GetUsuario;
import com.procol.procolombia.perfil.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GetUsuario>> crearUsuario(@RequestBody SaveUsuario saveUsuario) {
        GetUsuario usuario = usuarioService.crearUsuario(saveUsuario);
        return ResponseEntity.ok(ApiResponse.success("Usuario creado correctamente", usuario, HttpStatus.CREATED));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<GetUsuario>> actualizarUsuario(@PathVariable Integer id, @RequestBody SaveUsuario saveUsuario) {
        return ResponseEntity.ok(ApiResponse.success("Usuario actualizado correctamente", usuarioService.actualizarUsuario(id, saveUsuario), HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetUsuario>> getUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Usuario obtenido correctamente", usuarioService.obtenerUsuarioPorId(id), HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<List<GetUsuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(ApiResponse.success("Usuario eliminado correctamente", null, HttpStatus.OK));
    }
}
