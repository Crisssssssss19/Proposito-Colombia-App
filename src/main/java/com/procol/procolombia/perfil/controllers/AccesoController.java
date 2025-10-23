package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveAcceso;
import com.procol.procolombia.perfil.dtos.response.ApiResponse;
import com.procol.procolombia.perfil.dtos.response.GetAcceso;
import com.procol.procolombia.perfil.services.AccesoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accesos")
public class AccesoController {
    private final AccesoService accesoService;

    public AccesoController(AccesoService accesoService) {
        this.accesoService = accesoService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GetAcceso>> crearAcceso(@Valid @RequestBody SaveAcceso saveAcceso){
        return ResponseEntity.ok(ApiResponse.success("Acceso creado correctamente", accesoService.crearAcceso(saveAcceso), HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GetAcceso>> actualizarAcceso(@PathVariable Integer id, @RequestBody SaveAcceso saveAcceso){
        return ResponseEntity.ok(ApiResponse.success("Acceso Actualizado correctamente", accesoService.actualizarAcceso(id, saveAcceso), HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetAcceso>> obtenerAccesoPorId(@PathVariable Integer id){
        return ResponseEntity.ok(ApiResponse.success("Acceso obtenido correctamente", accesoService.obtenerAccesoPorUsuarioId(id), HttpStatus.OK));
    }
}
