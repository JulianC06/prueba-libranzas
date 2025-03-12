package com.julian.pruebalibranzas.repository;

import com.julian.pruebalibranzas.model.Cliente;
import com.julian.pruebalibranzas.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    boolean existsByCliente(Cliente cliente);
}
