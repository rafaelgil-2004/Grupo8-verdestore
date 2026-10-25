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
@Table(name = "itemcarrito")
@Schema(description = "Producto agregado a un carrito, con su cantidad")
public class ItemCarrito {

    @EmbeddedId
    @Schema(description = "Identificadores del carrito y del producto")
    private ItemCarritoId id;

    @MapsId("carritoId")
    @ManyToOne
    @JoinColumn(name = "Carrito_id", nullable = false)
    @Schema(description = "Carrito al que pertenece el ítem")
    private Carrito carrito;

    @MapsId("productoId")
    @ManyToOne
    @JoinColumn(name = "Producto_id", nullable = false)
    @Schema(description = "Producto agregado al carrito")
    private Producto producto;

    @Schema(description = "Cantidad del producto en el carrito", example = "2")
    private Integer cantidad;
}