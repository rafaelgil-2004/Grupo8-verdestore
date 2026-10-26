package com.aydsii.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aydsii.demo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}