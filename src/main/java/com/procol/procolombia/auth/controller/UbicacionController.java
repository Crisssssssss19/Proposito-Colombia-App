package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.UbicacionResponseDTO;
import com.procol.procolombia.auth.service.UbicacionService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicacion")
public class UbicacionController {

    private final UbicacionService ubicacionService;
    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'EMPRESA', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<List<UbicacionResponseDTO>>> getUbicaciones() {
        ApiResponseDTO<List<UbicacionResponseDTO>> ubicaciones = ubicacionService.listarUbicaciones();
        return ResponseEntity.status(ubicaciones.codigoEstado()).body(ubicaciones);
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'EMPRESA', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<List<UbicacionResponseDTO>>> getAutocompletado(@RequestParam String nombre) {
        return ResponseEntity.ok(ubicacionService.autocompletarUbicaciones(nombre));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'EMPRESA', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<UbicacionResponseDTO>> getUbicacionPorId(@PathVariable Integer id) {
        ApiResponseDTO<UbicacionResponseDTO> ubicacion = ubicacionService.obtenerUbicacionPorId(id);
        return ResponseEntity.status(ubicacion.codigoEstado()).body(ubicacion);
    }
}
