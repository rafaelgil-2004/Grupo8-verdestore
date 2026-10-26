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
@Table(name = "itemCarrito")
@IdClass(ItemCarritoId.class)
@Schema(description = "Producto agregado a un carrito, con su cantidad")
public class ItemCarrito {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "Carrito_id")
    private Carrito carrito;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "Producto_id")
    private Producto producto;

    @Schema(description = "Cantidad del producto en el carrito", example = "2")
    private Integer cantidad;
}