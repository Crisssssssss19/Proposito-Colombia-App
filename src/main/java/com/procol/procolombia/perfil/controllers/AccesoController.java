package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveAcceso;
import com.procol.procolombia.perfil.dtos.response.GetAcceso;
import com.procol.procolombia.perfil.services.AccesoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<GetAcceso> crearAcceso(@Valid @RequestBody SaveAcceso saveAcceso){
        GetAcceso acceso = accesoService.crearAcceso(saveAcceso);
        return ResponseEntity.status(201).body(acceso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetAcceso> actualizarAcceso(@PathVariable Integer id, @RequestBody SaveAcceso saveAcceso){
        return ResponseEntity.ok(accesoService.actualizarAcceso(saveAcceso, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAcceso> obtenerAccesoPorId(@PathVariable Integer id){
        return ResponseEntity.ok(accesoService.obtenerAccesoPorUsuarioId(id));
    }
}
