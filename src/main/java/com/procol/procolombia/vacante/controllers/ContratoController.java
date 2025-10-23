package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.dto.ContratoDto;
import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.Impl.ContratoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    private final ContratoServiceImpl contratoServiceImpl;

    public ContratoController(ContratoServiceImpl contratoServiceImpl) {
        this.contratoServiceImpl = contratoServiceImpl;
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<ApiResponse<ContratoDto>> obtenerContratoPorId(@PathVariable int id) {
        ContratoDto contrato = contratoServiceImpl.buscarContratoPorId(id);
        if(contrato!=null) {
            ApiResponse<ContratoDto> response = new ApiResponse<>(
                    200,
                    "Consulta exitosa",
                    contrato
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/obtener/todos")
    public ResponseEntity<ApiResponse<List<ContratoDto>>> obtenerContratos(){
        List<ContratoDto> contratos = contratoServiceImpl.listarContratos();
        ApiResponse<List<ContratoDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                contratos
        );
        return ResponseEntity.ok(response);
    }
}
