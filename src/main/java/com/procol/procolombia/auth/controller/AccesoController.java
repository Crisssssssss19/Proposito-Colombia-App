package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Request.AccesoRequestDTO;
import com.procol.procolombia.auth.dto.Request.LoginRequestDTO;
import com.procol.procolombia.auth.dto.Request.UserRegisterRequestDTO;
import com.procol.procolombia.auth.dto.Response.AccesoResponseDTO;
import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.dto.Response.LoginResponseDTO;
import com.procol.procolombia.auth.dto.Response.UserRegisterResponseDTO;
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

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<ApiResponseDTO<UserRegisterResponseDTO>> register(@RequestBody UserRegisterRequestDTO requestDTO) {
        ApiResponseDTO<UserRegisterResponseDTO> response = accesoService.register(requestDTO);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<ApiResponseDTO<AccesoResponseDTO>> crearAcceso(@RequestBody AccesoRequestDTO requestDTO) {
        ApiResponseDTO<AccesoResponseDTO> response = accesoService.crearAcceso(requestDTO);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @PutMapping("/{idAcceso}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<ApiResponseDTO<AccesoResponseDTO>> editarAcceso(
            @PathVariable Integer idAcceso,
            @RequestBody AccesoRequestDTO requestDTO
    ) {
        ApiResponseDTO<AccesoResponseDTO> response = accesoService.editarAcceso(idAcceso, requestDTO);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @DeleteMapping("/{idAcceso}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<ApiResponseDTO<String>> eliminarAcceso(@PathVariable Integer idAcceso) {
        ApiResponseDTO<String> response = accesoService.eliminarAcceso(idAcceso);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @GetMapping("/{idAcceso}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'TECNOLOGIA')")
    public ResponseEntity<ApiResponseDTO<AccesoResponseDTO>> obtenerAccesoPorId(@PathVariable Integer idAcceso) {
        ApiResponseDTO<AccesoResponseDTO> response = accesoService.obtenerAccesoPorId(idAcceso);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
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

    @GetMapping("/enviar-verificacion" )
    @PreAuthorize("hasAuthority('ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<String>> enviarVerificarCorreo(@RequestParam String correo) {
        ApiResponseDTO<String> response = accesoService.enviarVerificarCorreo(correo);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }

    @GetMapping("/verificar-correo" )
    @PreAuthorize("hasAuthority('ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<String>> verificarCorreo(@RequestParam Integer idUsuario, @RequestParam String uuid) {
        ApiResponseDTO<String> response = accesoService.verificarCorreo(idUsuario, uuid);
        return ResponseEntity.status(response.codigoEstado()).body(response);
    }
}
