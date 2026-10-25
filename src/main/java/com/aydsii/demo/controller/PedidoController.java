package com.aydsii.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;

import com.aydsii.demo.ApiResponse;
import com.aydsii.demo.model.HistorialRespuestaDTO;
import com.aydsii.demo.service.PedidoService;
import java.util.List;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/historial")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service){
        this.service = service;
    }

    @GetMapping("/historial")
    @Operation(
        summary = "Consultar historial de pedidos",
        description = "Devuelve los pedidos de un cliente con sus datos y productos"
    )
    public ResponseEntity<ApiResponse<List<HistorialRespuestaDTO>>> consultarHistorial(
            @Parameter(description = "ID del cliente", example = "1")
            @RequestParam Integer clienteId) {

        List<HistorialRespuestaDTO> historial = service.consultarHistorial(clienteId);

        if (historial.isEmpty()){
            return ResponseEntity.ok(new ApiResponse<>(200, "No hay pedidos realizados previamente", historial));
        }

        return ResponseEntity.ok(new ApiResponse<>(200, "Historial cargado correctamente", historial));
    }
}