package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.dto.HistorialInteresDto;
import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.Impl.HistorialInteresImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historiales")
public class HistorialInteresController {
    private final HistorialInteresImpl historialInteresImpl;

    public HistorialInteresController(HistorialInteresImpl historialInteresImpl) {
        this.historialInteresImpl = historialInteresImpl;
    }

    @GetMapping("/obtener/{id}")
    ResponseEntity<ApiResponse<HistorialInteresDto>> obtenerHistorialInteres(@PathVariable int id) {
            HistorialInteresDto historial = historialInteresImpl.buscarHistorialInteresPorId(id);
            if(historial!=null) {
                ApiResponse<HistorialInteresDto> response = new ApiResponse<>(
                        200,
                        "Consulta exitosa",
                        historial
                );
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/obtenertodos")
    ResponseEntity<ApiResponse<List<HistorialInteresDto>>> obtenerTodos() {
        List<HistorialInteresDto> historiales = historialInteresImpl.listarHistorialInteres();
        ApiResponse<List<HistorialInteresDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                historiales
        );
        return ResponseEntity.ok(response);
    }
}
