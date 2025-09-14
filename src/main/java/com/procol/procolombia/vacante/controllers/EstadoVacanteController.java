package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.EstadoVacanteService;
import com.procol.procolombia.vacante.dto.EstadosVacanteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadosVacantes")
public class EstadoVacanteController {
    private final EstadoVacanteService estadoVacanteService;

    public EstadoVacanteController(EstadoVacanteService estadoVacanteService) {
        this.estadoVacanteService = estadoVacanteService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<EstadosVacanteDto>>> getAllEstadosVacante() {
        List<EstadosVacanteDto> estados = estadoVacanteService.getAllEstadosVacante();
        ApiResponse<List<EstadosVacanteDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                estados
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping
    public ResponseEntity<ApiResponse<EstadosVacanteDto>> updateEstadoVacante(@RequestBody EstadosVacanteDto dto) {
        EstadosVacanteDto updated = estadoVacanteService.updateEstadoVacante(dto);
        ApiResponse<EstadosVacanteDto> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                updated
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<EstadosVacanteDto>> deleteEstadoVacante(@PathVariable Integer id) {
        EstadosVacanteDto estadoVacante = estadoVacanteService.findById(id);
        estadoVacanteService.deleteByEstadoVacanteId(id);

        ApiResponse<EstadosVacanteDto> response = new ApiResponse<>(
                200,
                "Estado de Vacante eliminado correctamente",
                estadoVacante
        );

        return ResponseEntity.ok(response);
    }
}
