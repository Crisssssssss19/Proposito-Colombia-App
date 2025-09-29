package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.dto.EmpresaDto;
import com.procol.procolombia.vacante.dto.JornadaDto;
import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.JornadaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Jornadas")
public class JornadaController {
    private final JornadaService jornadaService;
    public JornadaController(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<JornadaDto>> createJornada(@RequestBody JornadaDto jornadaDto) {
        JornadaDto created = jornadaService.createJornada(jornadaDto);
        ApiResponse<JornadaDto> response= new ApiResponse<>(
                201,
                "Jornada creada exitosamente",
                created
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<ApiResponse<JornadaDto>> updateRequisitos(
            @Valid @RequestBody JornadaDto jornadaDto){
        JornadaDto updated = jornadaService.updateJornada(jornadaDto);
        ApiResponse<JornadaDto> response = new ApiResponse<>(
                201,
                "Jornada actualizada exitosamente",
                updated
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> deleteJornada(@PathVariable Integer id){
        jornadaService.deleteJornada(id);
        ApiResponse<Integer> response = new ApiResponse<>(
                200,
                "Jornada eliminada exitosamente",
                id
        );
        return ResponseEntity.ok(response);
    }
}
