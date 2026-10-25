package com.aydsii.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import com.aydsii.demo.Estados;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resumen de un pedido con sus productos")
public class HistorialRespuestaDTO {
    
    @Schema(description = "Total del pedido", example = "9500")
    Double total;
 
    @Schema(description = "Fecha y hora en que se realizó el pedido", example = "2026-10-25T08:50:00")
    LocalDateTime fecha;
 
    @Schema(description = "Estado actual del pedido", example = "ACEPTADO")
    Estados estado;
 
    @Schema(description = "Últimos 4 dígitos de la tarjeta (nulo si no llegó a pedirse)", example = "4242")
    String ultimos4;

    @Schema(description = "Dirección de entrega", example = "Pago rechazado por la entidad bancaria: fondos insuficientes")
    private String direccion;

    @Schema(description = "Motivo del rechazo o la cancelación", example = "general pico calle 6 1578")
    private String motivo;

    @Schema(description = "Productos del pedido")
    List<ProductoHistorialDTO> productos;
}
