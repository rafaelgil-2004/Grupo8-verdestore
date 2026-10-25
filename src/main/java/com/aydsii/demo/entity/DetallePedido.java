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
@Table(name = "DetallePedido")
@Schema(description = "Línea de un pedido: un producto con su cantidad y el precio fijado al comprar")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la línea", example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "Pedido_id", nullable = false)
    @Schema(description = "Id del pedido al que pertenece la línea", example = "1")
    private Integer pedidoId;

    @Column(name = "Producto_id", nullable = false)
    @Schema(description = "Id del producto comprado", example = "1")
    private Integer productoId;

    @Schema(description = "Cantidad comprada", example = "2")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    @Schema(description = "Precio unitario al momento de la compra", example = "4200.0")
    private Double precioUnitario;
}
