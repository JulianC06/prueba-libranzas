package com.julian.pruebalibranzas.service;


import com.julian.pruebalibranzas.model.Cliente;
import com.julian.pruebalibranzas.model.Estado;
import com.julian.pruebalibranzas.model.Solicitud;
import com.julian.pruebalibranzas.repository.ClienteRepository;
import com.julian.pruebalibranzas.repository.SolicitudRepository;
import com.julian.pruebalibranzas.repository.EstadoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;
    private final EstadoRepository estadoRepository;

    public SolicitudService(SolicitudRepository solicitudRepository, ClienteRepository clienteRepository, EstadoRepository estadoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository = clienteRepository;
        this.estadoRepository = estadoRepository;
    }

    public List<Solicitud> getAllSolicitudes() {
        return solicitudRepository.findAll();
    }

    public Optional<Solicitud> getSolicitudById(Integer id) {
        return solicitudRepository.findById(id);
    }

    @Transactional
    public Solicitud createSolicitud(Solicitud solicitud) {
        Cliente cliente = solicitud.getCliente();
        Estado estado = solicitud.getEstado();

        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException("El cliente debe estar definido y debe tener un ID válido.");
        }
        if (estado == null || estado.getId() == null) {
            throw new IllegalArgumentException("El estado debe estar definido y debe tener un ID válido.");
        }

        Cliente clienteExistente = clienteRepository.findById(cliente.getId())
                .orElseThrow(() -> new IllegalArgumentException("El cliente no existe en la base de datos."));

        Estado estadoExistente = estadoRepository.findById(estado.getId())
                .orElseThrow(() -> new IllegalArgumentException("El estado no existe en la base de datos."));

        if (solicitudRepository.existsByCliente(clienteExistente)) {
            throw new IllegalArgumentException("El cliente ya tiene una solicitud registrada.");
        }

        if (solicitud.getFechaIngreso().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de ingreso no puede ser anterior a la fecha actual.");
        }

        if (solicitud.getMonto().compareTo(new BigDecimal("1000000")) < 0) {
            throw new IllegalArgumentException("El monto debe ser superior a 1 millón.");
        }

        solicitud.setCliente(clienteExistente);
        solicitud.setEstado(estadoExistente);

        return solicitudRepository.save(solicitud);
    }

    public Solicitud updateSolicitud(Integer id, Solicitud solicitud) {
        if (!solicitudRepository.existsById(id)) {
            throw new IllegalArgumentException("Solicitud no encontrada.");
        }

        solicitud.setId(id);
        return solicitudRepository.save(solicitud);
    }

    public void deleteSolicitud(Integer id) {
        if (!solicitudRepository.existsById(id)) {
            throw new IllegalArgumentException("Solicitud no encontrada.");
        }
        solicitudRepository.deleteById(id);
    }
}