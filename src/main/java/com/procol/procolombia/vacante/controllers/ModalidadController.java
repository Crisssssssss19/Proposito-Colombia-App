package com.procol.procolombia.vacante.controllers;

import com.procol.procolombia.vacante.dto.ModalidadDto;
import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.Impl.ModalidadServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/modalidades")
public class ModalidadController {

    private final ModalidadServiceImpl modalidadServiceImpl;

    public ModalidadController(ModalidadServiceImpl modalidadServiceImpl) {
        this.modalidadServiceImpl = modalidadServiceImpl;
    }

    @GetMapping("obtener/{id}")
    ResponseEntity<ApiResponse<ModalidadDto>> obtenerModalidad(@PathVariable int id) {
        ModalidadDto modalidad = modalidadServiceImpl.obtenerModalidadPorId(id);
        if(modalidad!=null){
            ApiResponse<ModalidadDto> response = new ApiResponse<>(
                    200,
                    "Consulta exitosa",
                    modalidad
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/obtener/todos")
    ResponseEntity<ApiResponse<List<ModalidadDto>>> obtenerModalidades() {
        List<ModalidadDto> modalidades = modalidadServiceImpl.listarModalidades();
        ApiResponse<List<ModalidadDto>> response = new ApiResponse<>(
                200,
                "Consuta exitosa",
                modalidades
        );
        return ResponseEntity.ok(response);
    }
}
