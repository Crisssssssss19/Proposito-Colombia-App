package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Request.UsuarioRequestDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.UsuarioResponseDTO;
import com.procol.procolombia.auth.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<UsuarioResponseDTO>>> getAllUsuarios() {
        ApiResponseDTO<List<UsuarioResponseDTO>> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.status(usuarios.codigoEstado()).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UsuarioResponseDTO>> getUsuario(@PathVariable Integer idUsuario) {
        ApiResponseDTO<UsuarioResponseDTO> usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
        return ResponseEntity.status(usuario.codigoEstado()).body(usuario);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<UsuarioResponseDTO>> createUsuario(@RequestBody UsuarioRequestDTO usuarioRequest) {
        ApiResponseDTO<UsuarioResponseDTO> usuario = usuarioService.crearUsuario(usuarioRequest);
        return ResponseEntity.status(usuario.codigoEstado()).body(usuario);
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponseDTO<List<UsuarioResponseDTO>>> getAllUsuariosByNombre(@RequestParam String nombre) {
        ApiResponseDTO<List<UsuarioResponseDTO>> usuarios = usuarioService.obtenerUsuarioPorNombre(nombre);
        return ResponseEntity.status(usuarios.codigoEstado()).body(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UsuarioResponseDTO>> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioRequestDTO usuarioRequest) {
        ApiResponseDTO<UsuarioResponseDTO> usuario = usuarioService.editarUsuario(id, usuarioRequest);
        return ResponseEntity.status(usuario.codigoEstado()).body(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<String>> deleteUsuario(@PathVariable Integer id) {
        ApiResponseDTO<String> usuario = usuarioService.eliminarUsuario(id);
        return ResponseEntity.status(usuario.codigoEstado()).body(usuario);
    }
}
