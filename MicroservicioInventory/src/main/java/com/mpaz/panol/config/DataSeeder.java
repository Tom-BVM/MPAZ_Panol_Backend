package com.mpaz.panol.config;

import com.mpaz.panol.model.Item;
import com.mpaz.panol.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner initItems(ItemRepository itemRepo) {
        return args -> {
            if (itemRepo.count() == 0) {
                itemRepo.save(new Item("Proyector Multimedia", "Epson PowerLite E20", "Proyector", 5, 5, "Disponible", "Estante A1 - Pañol"));
                itemRepo.save(new Item("Kit Robótica LEGO WeDo 2.0", "LEGO Education 45300", "Robótica", 8, 8, "Disponible", "Estante B3 - Laboratorio"));
                itemRepo.save(new Item("Placa Microcontrolador ESP32", "Espressif ESP32-WROOM", "Robótica", 15, 15, "Disponible", "Cajón Electrónica 2"));
                itemRepo.save(new Item("Notebook Docente", "Lenovo ThinkPad E14", "Notebook", 10, 9, "Disponible", "Carro Carga 1"));
                itemRepo.save(new Item("Switch Capa 2 24 Puertos", "TP-Link TL-SG1024D", "Equipamiento de red", 2, 2, "Disponible", "Rack Fijo Sala Computación"));
            }
        };
    }
}