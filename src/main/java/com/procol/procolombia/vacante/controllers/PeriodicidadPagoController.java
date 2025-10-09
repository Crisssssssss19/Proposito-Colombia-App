package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.dto.PeriodicidadPagoDto;
import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.Impl.PeriodicidadPagoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/periodicidades-pago")
public class PeriodicidadPagoController {

    private final PeriodicidadPagoServiceImpl periodicidadPagoServiceImpl;

    public PeriodicidadPagoController(PeriodicidadPagoServiceImpl periodicidadPagoServiceImpl) {
        this.periodicidadPagoServiceImpl = periodicidadPagoServiceImpl;
    }

    @GetMapping("/obtener/{id}")
    ResponseEntity<ApiResponse<PeriodicidadPagoDto>> obtenerPeriodicidadPago(@PathVariable int id) {
        PeriodicidadPagoDto periodicidad = periodicidadPagoServiceImpl.obtenerPeriodicidadPagoPorId(id);
        if (periodicidad != null) {
            ApiResponse<PeriodicidadPagoDto> response = new ApiResponse<>(
                    200,
                    "Consulta exitosa",
                    periodicidad
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/obtener/todos")
    ResponseEntity<ApiResponse<List<PeriodicidadPagoDto>>> obtenerPeriodicidadesPago() {
        List<PeriodicidadPagoDto> periodicidades = periodicidadPagoServiceImpl.listarPeriodicidadPagos();
        ApiResponse<List<PeriodicidadPagoDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                periodicidades
        );
        return ResponseEntity.ok(response);
    }
}

