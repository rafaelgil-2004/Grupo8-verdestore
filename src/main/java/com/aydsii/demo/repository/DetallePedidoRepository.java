package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aydsii.demo.entity.Cliente;
import com.aydsii.demo.entity.DetallePedido;
import com.aydsii.demo.entity.DetallePedidoId;

import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoId> {

    List<DetallePedido> findByPedidoIdIn(List<Integer> pedidoIds);
}