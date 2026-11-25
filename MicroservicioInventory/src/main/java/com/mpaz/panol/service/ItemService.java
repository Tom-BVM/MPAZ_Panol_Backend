package com.mpaz.panol.service;

import com.mpaz.panol.model.Item;
import com.mpaz.panol.repository.ItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository repo;

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public List<Item> findAll() {
        return repo.findAll();
    }

    public Optional<Item> findById(Long id) {
        return repo.findById(id);
    }

    public Item create(Item item) {
        return repo.save(item);
    }

    public Optional<Item> update(Long id, Item updated) {
        return repo.findById(id).map(item -> {
            item.setNombre(updated.getNombre());
            item.setMarcaModelo(updated.getMarcaModelo());
            item.setCategoria(updated.getCategoria());
            item.setStockTotal(updated.getStockTotal());
            item.setStockDisponible(updated.getStockDisponible());
            item.setEstado(updated.getEstado());
            item.setUbicacion(updated.getUbicacion());
            return repo.save(item);
        });
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}