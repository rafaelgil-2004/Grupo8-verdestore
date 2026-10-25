package com.aydsii.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detallepedido")
@Schema(description = "Línea de un pedido: un producto con su cantidad y el precio fijado al comprar")
public class DetallePedido {

    @EmbeddedId
    @Schema(description = "Identificadores del pedido y del producto")
    private DetallePedidoId id;

    @MapsId("pedidoId")
    @ManyToOne
    @JoinColumn(name = "Pedido_id", nullable = false)
    @Schema(description = "Pedido al que pertenece la línea")
    private Pedido pedido;

    @MapsId("productoId")
    @ManyToOne
    @JoinColumn(name = "Producto_id", nullable = false)
    @Schema(description = "Producto comprado")
    private Producto producto;

    @Schema(description = "Cantidad comprada", example = "2")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    @Schema(description = "Precio unitario al momento de la compra", example = "4200.0")
    private Double precioUnitario;
}