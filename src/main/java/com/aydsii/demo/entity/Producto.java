package com.aydsii.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Producto")
@Schema(description = "Producto del catálogo de VerdeGreen")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del producto", example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "Nombre del producto",
            example = "Vaso plástico reutilizable 400 ml")
    private String nombre;

    @Schema(description = "Descripción del producto",
            example = "Vaso reutilizable de PET reciclado, 400 ml, apto lavavajillas")
    private String descripcion;

    @Schema(description = "Precio vigente del producto", example = "4500.0")
    private Double precio;

    @Schema(description = "Indica si el producto está activo en el catálogo",
            example = "true")
    private Boolean activo;

    @Schema(description = "Unidades disponibles en stock", example = "120")
    private Integer stock;

    @Column(name = "stock_reservado")
    @Schema(description = "Unidades reservadas por compras en curso", example = "0")
    private Integer stockReservado;

    @Schema(description = "Categoría del producto", example = "Hogar")
    private String categoria;

    @Schema(description = "Peso en kilogramos", example = "0.08")
    private Double peso;

    @Schema(description = "Alto en centímetros", example = "12.0")
    private Double alto;

    @Schema(description = "Ancho en centímetros", example = "8.0")
    private Double ancho;

    @Schema(description = "Largo en centímetros", example = "8.0")
    private Double largo;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Schema(description = "Detalles de pedidos en los que aparece el producto")
    private List<DetallePedido> detallesPedido;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Schema(description = "Ítems de carritos que contienen el producto")
    private List<ItemCarrito> itemsCarrito;
}