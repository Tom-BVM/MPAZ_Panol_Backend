package com.mpaz.panol.controller;

import com.mpaz.panol.model.Reserva;
import com.mpaz.panol.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaController {
    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Reserva> getAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Reserva req) {
        try {
            return ResponseEntity.ok(service.createReserva(req));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> updateEstado(@PathVariable Long id,
                                          @RequestParam String nuevoEstado,
                                          @RequestParam(required = false) String comentario) {
        try {
            return ResponseEntity.ok(service.updateEstado(id, nuevoEstado, comentario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}