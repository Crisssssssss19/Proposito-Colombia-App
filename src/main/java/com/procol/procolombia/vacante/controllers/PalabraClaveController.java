package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.dto.EmpresaDto;
import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.PalabraClaveService;
import com.procol.procolombia.vacante.dto.PalabrasClaveDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/palabrasClave")
public class PalabraClaveController {
    private final PalabraClaveService palabraClaveService;

    public PalabraClaveController(PalabraClaveService palabraClaveService) {
        this.palabraClaveService = palabraClaveService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PalabrasClaveDto>>> getAllPalabrasClaves() {
        List<PalabrasClaveDto> palabrasClave = palabraClaveService.findAllPalabrasClaves();
        ApiResponse<List<PalabrasClaveDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                palabrasClave
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PalabrasClaveDto>> getPalabraClaveById(@PathVariable Integer id) {
        PalabrasClaveDto dto = palabraClaveService.findPalabraClaveById(id);
        ApiResponse<PalabrasClaveDto> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                dto
        );
        return dto != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PalabrasClaveDto>> deletePalabraClave(@PathVariable Integer id) {
        PalabrasClaveDto palabraClave = palabraClaveService.findPalabraClaveById(id);
        palabraClaveService.deletePalabraClave(id);

        ApiResponse<PalabrasClaveDto> response = new ApiResponse<>(
                200,
                "Palabra Clave eliminada correctamente",
                palabraClave
        );
        return ResponseEntity.ok(response);
    }
}
