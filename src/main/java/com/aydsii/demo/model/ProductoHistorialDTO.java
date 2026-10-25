package com.aydsii.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.aydsii.demo.Estados;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de un producto visibles en el historial")
public class ProductoHistorialDTO {

    @Schema(description = "Nombre del producto", example = "Cesto papelero")
    String nombre;
 
    @Schema(description = "Descripción del producto", example = "Cesto papelero de 10 litros de PET reciclado")
    String descripcion;
 
    @Schema(description = "Cantidad comprada", example = "1")
    Integer cantidad;
 
    @Schema(description = "Total de la línea: cantidad por precio unitario fijado al comprar", example = "6900")
    Double total;
}
