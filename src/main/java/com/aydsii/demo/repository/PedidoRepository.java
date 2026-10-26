package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.aydsii.demo.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByClienteIdOrderByFechaRealizacionDesc(Integer clienteId);
}