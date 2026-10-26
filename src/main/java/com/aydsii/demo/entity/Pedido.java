package com.aydsii.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "Cliente_id", nullable = false)
    @Schema(description = "Cliente que realizó el pedido")
    private Cliente cliente;

    @Column(name = "fecha_realizacion", nullable = false)
    @Schema(description = "Fecha y hora en que se realizó el pedido",
            example = "2026-10-25T08:50:00")
    private LocalDateTime fechaRealizacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Schema(description = "Estado actual del pedido", example = "ACEPTADO")
    private Estados estado;

    @Schema(description = "Total del pedido, con los precios fijados al comprar",
            example = "18100.0")
    private Double total;

    @Column(name = "tipo_tarjeta")
    @Schema(description = "Tipo de tarjeta usada (nulo si se rechazó antes de pedirla)",
            example = "Visa")
    private String tipoTarjeta;

    @Column(name = "ultimos_4")
    @Schema(description = "Últimos 4 dígitos de la tarjeta usada", example = "4242")
    private String ultimos4;

    @Schema(description = "Motivo del rechazo o la cancelación",
            example = "Pago rechazado por la entidad bancaria: fondos insuficientes")
    private String motivo;

    @OneToMany(mappedBy = "pedido")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Schema(description = "Detalles de productos incluidos en el pedido")
    private List<DetallePedido> detalles;

    @Column(name = "direccion_envio")
    @Schema(description = "direccion de envio de pedido",
            example = "general pico calle 6 1578")
    private String direccion;
}