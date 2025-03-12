package com.julian.pruebalibranzas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.julian.pruebalibranzas.service.SolicitudService;
import com.julian.pruebalibranzas.model.Solicitud;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping
    public List<Solicitud> getAllSolicitudes() {
        return solicitudService.getAllSolicitudes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable Integer id) {
        Optional<Solicitud> solicitud = solicitudService.getSolicitudById(id);
        return solicitud.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createSolicitud(@RequestBody Solicitud solicitud) {
        try {
            return ResponseEntity.ok(solicitudService.createSolicitud(solicitud));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSolicitud(@PathVariable Integer id, @RequestBody Solicitud solicitud) {
        try {
            return ResponseEntity.ok(solicitudService.updateSolicitud(id, solicitud));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSolicitud(@PathVariable Integer id) {
        try {
            solicitudService.deleteSolicitud(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

