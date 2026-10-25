package com.aydsii.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carrito")
@Schema(description = "Carrito de compras de un cliente")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del carrito", example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "Cliente_id", nullable = false, unique = true)
    @Schema(description = "Cliente dueño del carrito")
    private Cliente cliente;

    @OneToMany(mappedBy = "carrito")
    @Schema(description = "Ítems que contiene el carrito")
    private List<ItemCarrito> items;
}