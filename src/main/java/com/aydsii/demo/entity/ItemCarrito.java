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
@Schema(description = "Producto agregado a un carrito, con su cantidad")
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del ítem", example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "Carrito_id", nullable = false)
    @Schema(description = "Id del carrito al que pertenece el ítem", example = "1")
    private Integer carritoId;

    @Column(name = "Producto_id", nullable = false)
    @Schema(description = "Id del producto agregado al carrito", example = "1")
    private Integer productoId;

    @Schema(description = "Cantidad del producto en el carrito", example = "2")
    private Integer cantidad;
}