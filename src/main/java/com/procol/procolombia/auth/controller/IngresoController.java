package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.IngresoResponseDTO;
import com.procol.procolombia.auth.service.IngresoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingreso")
public class IngresoController {

    private final IngresoService ingresoService;
    public IngresoController(IngresoService ingresoService) {
        this.ingresoService = ingresoService;
    }

    @GetMapping("/usuario/{idUsuario}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<List<IngresoResponseDTO>>> getPorUsuario(@PathVariable Integer idUsuario) {
        ApiResponseDTO<List<IngresoResponseDTO>> ingreso = ingresoService.listarIngresosPorUsuario(idUsuario);
        return ResponseEntity.status(ingreso.codigoEstado()).body(ingreso);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<IngresoResponseDTO>> createIngreso(@RequestParam Integer idAcceso){
        ApiResponseDTO<IngresoResponseDTO> ingreso = ingresoService.crearIngreso(idAcceso);
        return ResponseEntity.status(ingreso.codigoEstado()).body(ingreso);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ApiResponseDTO<List<IngresoResponseDTO>>> listarIngresos() {
        ApiResponseDTO<List<IngresoResponseDTO>> ingresos = ingresoService.listarIngresos();
        return ResponseEntity.status(ingresos.codigoEstado()).body(ingresos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'TECNOLOGIA')")
    public ResponseEntity<ApiResponseDTO<IngresoResponseDTO>> getIngreso(@PathVariable Integer id) {
        ApiResponseDTO<IngresoResponseDTO> ingreso = ingresoService.buscarIngresoPorId(id);
        return ResponseEntity.status(ingreso.codigoEstado()).body(ingreso);
    }
}
