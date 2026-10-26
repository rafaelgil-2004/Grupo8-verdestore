package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aydsii.demo.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Verifica y descuenta en una sola sentencia: dos compras simultáneas no pueden llevarse la misma unidad
    @Modifying
    @Query("UPDATE Producto p SET p.stock = p.stock - :cant " +
           "WHERE p.id = :id AND p.activo = true AND p.stock >= :cant")
    int descontarStock(@Param("id") Integer id, @Param("cant") Integer cant);
}