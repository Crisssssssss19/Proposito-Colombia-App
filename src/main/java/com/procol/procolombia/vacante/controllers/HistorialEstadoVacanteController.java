package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.entities.HistorialEstadoVacante;
import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.HistorialEstadoVacanteService;
import com.procol.procolombia.vacante.dto.HistorialEstadoVacanteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historialEstadosVacantes")
public class HistorialEstadoVacanteController {
    private final HistorialEstadoVacanteService historialEstadoVacanteService;

    public HistorialEstadoVacanteController(HistorialEstadoVacanteService historialEstadoVacanteService) {
        this.historialEstadoVacanteService = historialEstadoVacanteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HistorialEstadoVacanteDto>> getById(@PathVariable Integer id) {
        HistorialEstadoVacanteDto dto = historialEstadoVacanteService.findById(id);
        ApiResponse<HistorialEstadoVacanteDto> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                dto
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vacante/{vacanteId}")
    public ResponseEntity<ApiResponse<List<HistorialEstadoVacanteDto>>> getByVacanteId(@PathVariable Integer vacanteId) {
        List<HistorialEstadoVacanteDto> historial = historialEstadoVacanteService.findByVacanteId(vacanteId);
        ApiResponse<List<HistorialEstadoVacanteDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                historial
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<HistorialEstadoVacanteDto>> deleteHistorialEstadoVacante(@RequestBody HistorialEstadoVacanteDto historialEstadoVacanteDto
    ) {
        historialEstadoVacanteService.deleteHistorialEstadoVacante(historialEstadoVacanteDto);

        ApiResponse<HistorialEstadoVacanteDto> response = new ApiResponse<>(
                200,
                "Historial eliminado correctamente",
                historialEstadoVacanteDto
        );
        return ResponseEntity.ok(response);
    }
}
