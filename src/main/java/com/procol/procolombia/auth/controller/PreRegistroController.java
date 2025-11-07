package com.procol.procolombia.auth.controller;

import com.procol.procolombia.auth.dto.Response.ApiResponseDTO;
import com.procol.procolombia.auth.service.AccesoService;
import com.procol.procolombia.auth.service.PreRegistroService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/preregistro")
public class PreRegistroController {

    private final PreRegistroService preRegistroService;
    private final AccesoService accesoService;

    public PreRegistroController(PreRegistroService preRegistroService, AccesoService accesoService) {
        this.preRegistroService = preRegistroService;
        this.accesoService = accesoService;
    }

    // 🔹 1️⃣ Enviar código SMS (flujo general de preregistro)
    @PostMapping("/enviar")
    public ResponseEntity<ApiResponseDTO<String>> enviarCodigo(@RequestParam String telefono) {
        ApiResponseDTO<String> apiResponseDTO = preRegistroService.enviarCodigo(telefono);
        return ResponseEntity.status(apiResponseDTO.codigoEstado()).body(apiResponseDTO);
    }

    // 🔹 2️⃣ Validar código (flujo general de preregistro)
    @PostMapping("/validar")
    public ResponseEntity<ApiResponseDTO<String>> validarCodigo(
            @RequestParam String telefono,
            @RequestParam String codigo) {
        ApiResponseDTO<String> apiResponseDTO = preRegistroService.validarCodigo(telefono, codigo);
        return ResponseEntity.status(apiResponseDTO.codigoEstado()).body(apiResponseDTO);
    }

    // ✅ 3️⃣ Enviar código al teléfono actual (para confirmar cambio)
    @PostMapping("/enviar-cambio")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'TECNOLOGIA', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<String>> enviarCodigoCambio(@RequestParam Integer idUsuario) {
        ApiResponseDTO<String> telefonoResponse = accesoService.obtenerTelefonoAcceso(idUsuario);

        if (telefonoResponse.datos() == null || telefonoResponse.datos().isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new ApiResponseDTO<>(400, "No hay teléfono registrado", null, LocalDateTime.now().toString())
            );
        }

        ApiResponseDTO<String> envioResponse = preRegistroService.enviarCodigoCambio(telefonoResponse.datos());
        return ResponseEntity.status(envioResponse.codigoEstado()).body(envioResponse);
    }

    // ✅ 4️⃣ Validar código recibido y actualizar teléfono
    @PostMapping("/verificar-cambio")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'TECNOLOGIA', 'ASPIRANTE')")
    public ResponseEntity<ApiResponseDTO<String>> verificarCambio(
            @RequestParam Integer idUsuario,
            @RequestParam String codigo,
            @RequestParam String nuevoTelefono) {

        ApiResponseDTO<String> telefonoResponse = accesoService.obtenerTelefonoAcceso(idUsuario);
        String telefonoActual = telefonoResponse.datos();

        ApiResponseDTO<String> verificacion = preRegistroService.validarCodigo(telefonoActual, codigo);

        if (verificacion.codigoEstado() == 200) {
            ApiResponseDTO<String> updateResponse = accesoService.actualizarTelefonoAcceso(idUsuario, nuevoTelefono);
            return ResponseEntity.ok(updateResponse);
        } else {
            return ResponseEntity.status(verificacion.codigoEstado()).body(verificacion);
        }
    }
}
