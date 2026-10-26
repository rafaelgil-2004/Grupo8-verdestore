package com.aydsii.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aydsii.demo.service.CompraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/compra")
@RequiredArgsConstructor
@Tag(name = "Compras", description = "Compra del carrito o de un producto puntual")
public class CompraController {

    private final CompraService compraService;

    @Operation(summary = "Comprar todo el contenido del carrito del cliente")
    @PostMapping("/carrito")
    public ResponseEntity<Map<String, Object>> carrito(
            @RequestParam Integer clienteId,
            @RequestParam String numeroTarjeta,
            @RequestParam String direccion) {
        return ResponseEntity.ok(compraService.comprar(clienteId, numeroTarjeta, direccion));
    }

    @Operation(summary = "Comprar un único producto (cantidad 1 si no se indica)")
    @PostMapping("/compraUnitaria")
    public ResponseEntity<Map<String, Object>> compraUnitaria(
            @RequestParam Integer clienteId,
            @RequestParam Integer productoId,
            @RequestParam(defaultValue = "1") Integer cantidad,
            @RequestParam String numeroTarjeta,
            @RequestParam String direccion) {
        return ResponseEntity.ok(
                compraService.compraUnitaria(clienteId, productoId, cantidad, numeroTarjeta, direccion));
    }
}