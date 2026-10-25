package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aydsii.demo.entity.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {

}