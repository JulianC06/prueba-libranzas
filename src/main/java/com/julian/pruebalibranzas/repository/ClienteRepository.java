package com.julian.pruebalibranzas.repository;

import com.julian.pruebalibranzas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
