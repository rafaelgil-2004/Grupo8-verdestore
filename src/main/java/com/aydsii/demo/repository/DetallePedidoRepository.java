package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aydsii.demo.entity.DetallePedido;
import com.aydsii.demo.entity.DetallePedidoId;

import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoId> {

    @Query("SELECT d FROM DetallePedido d " +
           "JOIN FETCH d.producto " +
           "JOIN FETCH d.pedido " +
           "WHERE d.pedido.id IN :pedidoIds")
    List<DetallePedido> findByPedidoIdIn(@Param("pedidoIds") List<Integer> pedidoIds);
}
