package com.aydsii.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aydsii.demo.entity.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

    Optional<Carrito> findByClienteId(Integer clienteId);
}