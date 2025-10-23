package com.procol.procolombia.vacante.controllers;


import com.procol.procolombia.vacante.response.ApiResponse;
import com.procol.procolombia.vacante.services.RequisitoService;
import com.procol.procolombia.vacante.dto.RequisitoDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/requisitos")
public class RequisitoController {
    private final RequisitoService requisitoService;

    public RequisitoController(RequisitoService requisitoService) {
        this.requisitoService = requisitoService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RequisitoDto>> createRequisito(@RequestBody RequisitoDto requisitoDto) {
        RequisitoDto created = requisitoService.createRequisito(requisitoDto);
        ApiResponse<RequisitoDto> response = new ApiResponse<>(
                201,
                "Requisito creado exitosamente",
                created
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RequisitoDto>> updateRequisitos(
            @PathVariable Integer id,
            @Valid @RequestBody RequisitoDto requisitoDto) {

        RequisitoDto updated = requisitoService.updateRequisito(id, requisitoDto);
        ApiResponse<RequisitoDto> response = new ApiResponse<>(
                201,
                "Requisito actualizado exitosamente",
                updated
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> deleteRequisito(@PathVariable Integer id) {
        requisitoService.deleteRequisito(id);
        ApiResponse<Integer> response = new ApiResponse<>(
                200,
                "Requisito eliminado exitosamente",
                id
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RequisitoDto>>> getAllRequisitos() {
        List<RequisitoDto> lista = requisitoService.findAllRequisitos();

        ApiResponse<List<RequisitoDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                lista
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vacante/{idVacante}")
    public ResponseEntity<ApiResponse<List<RequisitoDto>>> getRequisitosByIdVacante(@PathVariable Integer idVacante) {
        List<RequisitoDto> lista = requisitoService.getRequisitosByIdVacante(idVacante);

        ApiResponse<List<RequisitoDto>> response = new ApiResponse<>(
                200,
                "Consulta exitosa",
                lista
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<ApiResponse<RequisitoDto>> getRequisitoById(@PathVariable Integer id){
        RequisitoDto requisito = requisitoService.getRequisitoById(id);
        if(requisito != null){
            ApiResponse<RequisitoDto> response = new ApiResponse<>(
                    200,
                    "Requisito encontrado por id",
                    requisito
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
