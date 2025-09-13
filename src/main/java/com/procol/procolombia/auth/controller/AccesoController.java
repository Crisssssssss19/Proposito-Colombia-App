package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Request.AccesoRequestDTO;
import com.procol.procolombia.auth.dto.Request.LoginRequestDTO;
import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.LoginResponseDTO;
import com.procol.procolombia.auth.service.AccesoService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acceso")
public class AccesoController {

    private final AccesoService accesoService;

    public AccesoController(AccesoService accesoService) {
        this.accesoService = accesoService;
    }

    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<ApiResponseDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO requestDTO) {
        ApiResponseDTO<LoginResponseDTO> response = accesoService.login(requestDTO);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<ApiResponseDTO<AccesoResponseDTO>> crearAcceso(@RequestBody AccesoRequestDTO requestDTO) {
        ApiResponseDTO<AccesoResponseDTO> response = accesoService.crearAcceso(requestDTO);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @PutMapping("/{idAcceso}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<ApiResponseDTO<AccesoResponseDTO>> editarAcceso(
            @PathVariable Integer idAcceso,
            @RequestBody AccesoRequestDTO requestDTO
    ) {
        ApiResponseDTO<AccesoResponseDTO> response = accesoService.editarAcceso(idAcceso, requestDTO);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @DeleteMapping("/{idAcceso}")
    @PreAuthorize("hasRole('Administrador')")
    public ResponseEntity<ApiResponseDTO<String>> eliminarAcceso(@PathVariable Integer idAcceso) {
        ApiResponseDTO<String> response = accesoService.eliminarAcceso(idAcceso);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @GetMapping("/{idAcceso}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'Tecnologia')")
    public ResponseEntity<ApiResponseDTO<AccesoResponseDTO>> obtenerAccesoPorId(@PathVariable Integer idAcceso) {
        ApiResponseDTO<AccesoResponseDTO> response = accesoService.obtenerAccesoPorId(idAcceso);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<ApiResponseDTO<List<AccesoResponseDTO>>> listarAccesos() {
        ApiResponseDTO<List<AccesoResponseDTO>> response = accesoService.ListarAcceso();
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @PutMapping("/{idUsuario}/clave")
    @PermitAll
    public ResponseEntity<ApiResponseDTO<String>> cambiarClave(
            @PathVariable Integer idUsuario,
            @RequestParam String nuevaClave
    ) {
        ApiResponseDTO<String> response = accesoService.cambiarClave(idUsuario, nuevaClave);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }
}
