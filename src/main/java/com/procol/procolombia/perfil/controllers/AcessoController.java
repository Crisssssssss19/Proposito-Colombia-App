package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SaveAcceso;
import com.procol.procolombia.perfil.dtos.response.GetAcceso;
import com.procol.procolombia.perfil.services.AccesoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accesos")
public class AcessoController {
    private final AccesoService accesoService;

    public AcessoController(AccesoService accesoService) {
        this.accesoService = accesoService;
    }

    @PostMapping
    public ResponseEntity<GetAcceso> crearAcceso(@RequestBody SaveAcceso saveAcceso){
        return ResponseEntity.ok(accesoService.crearAcceso(saveAcceso));
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
