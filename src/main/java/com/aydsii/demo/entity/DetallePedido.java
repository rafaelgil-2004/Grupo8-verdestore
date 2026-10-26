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
@IdClass(DetallePedidoId.class)
@Schema(description = "Línea de un pedido: un producto con su cantidad y el precio fijado al comprar")
public class DetallePedido {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "Pedido_id")
    private Pedido pedido;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "Producto_id")
    private Producto producto;

    @Schema(description = "Cantidad comprada", example = "2")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    @Schema(description = "Precio unitario al momento de la compra", example = "4200.0")
    private Double precioUnitario;
}