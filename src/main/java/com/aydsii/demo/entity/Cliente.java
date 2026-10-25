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
@Table(name = "Cliente")
@Schema(description = "Cliente que realiza pedidos")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del cliente", example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Schema(description = "Nombre del cliente", example = "Lucía")
    private String nombre;

    @Schema(description = "Apellido del cliente", example = "Fernández")
    private String apellido;

    @Schema(description = "Teléfono del cliente", example = "+54 2302 456789")
    private String telefono;

    @Schema(description = "Email del cliente", example = "lucia.fernandez@example.com")
    private String email;
}