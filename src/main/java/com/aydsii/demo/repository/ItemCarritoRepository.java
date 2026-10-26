package com.aydsii.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aydsii.demo.entity.ItemCarrito;
import com.aydsii.demo.entity.ItemCarritoId;

public interface ItemCarritoRepository extends JpaRepository<ItemCarrito, ItemCarritoId> {

    @Query("SELECT i FROM ItemCarrito i WHERE i.carrito.id = :carritoId")
    List<ItemCarrito> buscarPorCarritoId(@Param("carritoId") Integer carritoId);

    // DELETE masivo en una sola sentencia; requiere transacción activa (la abre CompraService)
    @Modifying
    @Query("DELETE FROM ItemCarrito i WHERE i.carrito.id = :carritoId")
    int eliminarPorCarritoId(@Param("carritoId") Integer carritoId);
}