package com.mpaz.panol.service;

import com.mpaz.panol.model.Reserva;
import com.mpaz.panol.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ReservaService {
    private final ReservaRepository repo;
    private final RestClient restClient = RestClient.create();
    private final String INVENTORY_URL = "http://inventory-service:8082/api/items";

    public ReservaService(ReservaRepository repo) {
        this.repo = repo;
    }

    public List<Reserva> findAll() {
        return repo.findAll();
    }

    public Reserva createReserva(Reserva req) {
        if (req.getFechaHoraInicio() != null &&
            req.getFechaHoraInicio().isBefore(LocalDateTime.now().plusHours(3))) {
            throw new IllegalArgumentException("La reserva debe realizarse con al menos 3 horas de anticipación.");
        }
        req.setEstado("PENDIENTE");
        req.setFechaCreacion(LocalDateTime.now());
        return repo.save(req);
    }

    @SuppressWarnings("unchecked")
    public Reserva updateEstado(Long id, String nuevoEstado, String comentario) {
        Reserva res = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        if ("RECHAZADA".equalsIgnoreCase(nuevoEstado) && (comentario == null || comentario.isBlank())) {
            throw new IllegalArgumentException("El comentario de gestión es obligatorio al rechazar.");
        }

        try {
            Map<String, Object> item = restClient.get()
                    .uri(INVENTORY_URL + "/" + res.getItemId())
                    .retrieve()
                    .body(Map.class);

            if (item != null) {
                int stockDisp = ((Number) item.get("stockDisponible")).intValue();
                int stockTotal = ((Number) item.get("stockTotal")).intValue();

                if ("ACEPTADA".equalsIgnoreCase(nuevoEstado) && !"ACEPTADA".equalsIgnoreCase(res.getEstado())) {
                    item.put("stockDisponible", Math.max(0, stockDisp - res.getCantidad()));
                    restClient.put()
                            .uri(INVENTORY_URL + "/" + res.getItemId())
                            .body(item)
                            .retrieve()
                            .toBodilessEntity();
                } else if ("DEVUELTA".equalsIgnoreCase(nuevoEstado)) {
                    item.put("stockDisponible", Math.min(stockTotal, stockDisp + res.getCantidad()));
                    restClient.put()
                            .uri(INVENTORY_URL + "/" + res.getItemId())
                            .body(item)
                            .retrieve()
                            .toBodilessEntity();
                }
            }
        } catch (Exception e) {
            System.err.println("Aviso: No se pudo sincronizar stock con inventory-service: " + e.getMessage());
        }

        res.setEstado(nuevoEstado.toUpperCase());
        if (comentario != null) res.setComentarioGestion(comentario);
        return repo.save(res);
    }
}