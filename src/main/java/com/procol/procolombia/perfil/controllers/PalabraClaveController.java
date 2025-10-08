package com.procol.procolombia.perfil.controllers;

import com.procol.procolombia.perfil.dtos.request.SavePalabraClave;
import com.procol.procolombia.perfil.dtos.response.ApiResponse;
import com.procol.procolombia.perfil.dtos.response.GetPalabraClave;
import com.procol.procolombia.perfil.services.PalabraClaveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabras-clave")
public class PalabraClaveController {

    private final PalabraClaveService palabraClaveService;

    public PalabraClaveController(PalabraClaveService palabraClaveService) {
        this.palabraClaveService = palabraClaveService;
    }

    @PostMapping("/crear")
    public ResponseEntity<ApiResponse<GetPalabraClave>> crearPalabraClave(@Valid @RequestBody SavePalabraClave savePalabraClave) {
        GetPalabraClave palabraClaveCreada = palabraClaveService.crearPalabraClave(savePalabraClave);
        return ResponseEntity.ok(ApiResponse.success("Palabra clave creada correctamente", palabraClaveCreada, org.springframework.http.HttpStatus.CREATED));
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponse<List<GetPalabraClave>>> listarPalabrasClave(){
        return ResponseEntity.ok(ApiResponse.success("Lista de palabras clave obtenida correctamente", palabraClaveService.listarPalabrasClave(), org.springframework.http.HttpStatus.OK));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GetPalabraClave>> getPalabraClavePorId(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success("Palabra clave obtenida correctamente", palabraClaveService.obtenerPalabraClavePorId(id), org.springframework.http.HttpStatus.OK));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ApiResponse<GetPalabraClave>> actualizarPalabraClave(@PathVariable Integer id, @Valid @RequestBody SavePalabraClave savePalabraClave) {
        return ResponseEntity.ok(ApiResponse.success("Palabra clave actualizada correctamente", palabraClaveService.actualizarPalabraClave(id, savePalabraClave), org.springframework.http.HttpStatus.OK));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarPalabraClave(@PathVariable Integer id) {
        palabraClaveService.eliminarPalabraClave(id);
        return ResponseEntity.ok(ApiResponse.success("Palabra clave eliminada correctamente", null, HttpStatus.OK));
    }

}
