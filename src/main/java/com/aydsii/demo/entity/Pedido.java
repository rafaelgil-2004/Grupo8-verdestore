package com.aydsii.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import com.aydsii.demo.Estados;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Pedido")
@Schema(description = "Pedido realizado por un cliente")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del pedido", example = "1",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "Cliente_id", nullable = false)
    @Schema(description = "Id del cliente que realizó el pedido", example = "1")
    private Integer clienteId;

    @Column(name = "fecha_realizacion", nullable = false)
    @Schema(description = "Fecha y hora en que se realizó el pedido", example = "2026-10-25T08:50:00")
    private LocalDateTime fechaRealizacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Schema(description = "Estado actual del pedido", example = "ACEPTADO")
    private Estados estado;

    @Schema(description = "Total del pedido, con los precios fijados al comprar", example = "18100.0")
    private Double total;

    @Column(name = "tipo_tarjeta")
    @Schema(description = "Tipo de tarjeta usada (nulo si se rechazó antes de pedirla)", example = "Visa")
    private String tipoTarjeta;

    @Column(name = "ultimos_4")
    @Schema(description = "Últimos 4 dígitos de la tarjeta usada", example = "4242")
    private String ultimos4;

    @Schema(description = "Motivo del rechazo o la cancelación (nulo en otros estados)",
            example = "Pago rechazado por la entidad bancaria: fondos insuficientes")
    private String motivo;

}
