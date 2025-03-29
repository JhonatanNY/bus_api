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

import com.example.demo.model.Marca;
import com.example.demo.repository.IMarcaRepository;

@RestController
@RequestMapping("/marca")
@CrossOrigin(origins = "http://localhost:3000")
public class MarcaController {

    private final IMarcaRepository marcaRepository;

    public MarcaController(IMarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @GetMapping
    public List<Marca> obtenerMarcas() {
        return marcaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Marca obtenerMarcaPorId(@PathVariable Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
    }

    @PostMapping
    public ResponseEntity<Marca> agregarMarca(@RequestBody Marca marca) {
        Marca savedMarca = marcaRepository.save(marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMarca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        if (marcaRepository.existsById(id)) {
            marcaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}