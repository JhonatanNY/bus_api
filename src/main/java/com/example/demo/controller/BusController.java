package com.example.demo.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Bus;
import com.example.demo.model.Marca;
import com.example.demo.repository.IBusRepository;
import com.example.demo.repository.IMarcaRepository;

@RestController
@RequestMapping("/bus")
@CrossOrigin(origins = "http://localhost:3000") 
public class BusController {
	
    private final IBusRepository busRepository;
    private final IMarcaRepository marcaRepository;

    public BusController(IBusRepository busRepository, IMarcaRepository marcaRepository) {
        this.busRepository = busRepository;
        this.marcaRepository = marcaRepository;
    }
	
	@GetMapping
    public List<Bus> obtenerBuses() {
        return busRepository.findAll();
    }

    @GetMapping("/{id}")
    public Bus obtenerBusPorId(@PathVariable Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus no encontrado"));
    }
    
    @PostMapping
    public ResponseEntity<Bus> agregarBus(@RequestBody Bus bus) {
        // Verifica que la marca exista antes de asociarla al bus
        Marca marca = marcaRepository.findById(bus.getMarca().getIdMarca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));

        bus.setMarca(marca); // Establecer la marca del bus

        Bus savedBus = busRepository.save(bus);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBus);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        if (busRepository.existsById(id)) {
            busRepository.deleteById(id);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}

