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
@Table(name = "Carrito")
@Schema(description = "Carrito de compras de un cliente")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del carrito", example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "Cliente_id", nullable = false, unique = true)
    @Schema(description = "Id del cliente dueño del carrito", example = "1")
    private Integer clienteId;
}
