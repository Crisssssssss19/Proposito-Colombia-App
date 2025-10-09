package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.dto.RangoSalarialDto;
import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.Impl.RangoSalarialServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rangos-salariales")
public class RangoSalarialController {

    private final RangoSalarialServiceImpl rangoSalarialServiceImpl;

    public RangoSalarialController(RangoSalarialServiceImpl rangoSalarialServiceImpl) {
        this.rangoSalarialServiceImpl = rangoSalarialServiceImpl;
    }

    @GetMapping("/obtener/{id}")
    ResponseEntity<ApiResponse<RangoSalarialDto>> obtenerRangoSalarial(@PathVariable int id) {
        RangoSalarialDto rango = rangoSalarialServiceImpl.obtenerRangoSalarialPorId(id);
        if (rango != null) {
            ApiResponse<RangoSalarialDto> response = new ApiResponse<>(
                    200,
                    "Consulta exitosa",
                    rango
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/obtener/todos")
    ResponseEntity<ApiResponse<List<RangoSalarialDto>>> obtenerRangosSalariales() {
        List<RangoSalarialDto> rangos = rangoSalarialServiceImpl.listarRangoSalarial();
        ApiResponse<List<RangoSalarialDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                rangos
        );
        return ResponseEntity.ok(response);
    }
}
