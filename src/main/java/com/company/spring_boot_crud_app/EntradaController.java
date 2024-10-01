package com.company.spring_boot_crud_app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entradas")
public class EntradaController {

    private final EntradaRepository entradaRepository;
    private final EtiquetaRepository etiquetaRepository;

    public EntradaController(EntradaRepository entradaRepository, EtiquetaRepository etiquetaRepository) {
        this.entradaRepository = entradaRepository;
        this.etiquetaRepository = etiquetaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Entrada>> obtenerTodos() {
        List<Entrada> entradas = (List<Entrada>) entradaRepository.findAll();
        return ResponseEntity.ok(entradas);
    }





    @GetMapping("/{id}")
    public ResponseEntity<Entrada> obtenerPorId(@PathVariable Long id) {
        return entradaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }





    
    @PostMapping
    public ResponseEntity<Entrada> crearProducto(@RequestBody Entrada entrada) {
        if (entrada.getEtiqueta() != null && entrada.getEtiqueta().getId() != null) {
            Optional<Etiqueta> etiqueta = etiquetaRepository.findById(entrada.getEtiqueta().getId());
            if (etiqueta.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            entrada.setEtiqueta(etiqueta.get());
        }
        Entrada nuevoProducto = entradaRepository.save(entrada);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<Entrada> actualizarProducto(@PathVariable Long id, @RequestBody Entrada entrada) {
        if (!entradaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        entrada.setId(id);
        if (entrada.getEtiqueta() != null && entrada.getEtiqueta().getId() != null) {
            Optional<Etiqueta> etiqueta = etiquetaRepository.findById(entrada.getEtiqueta().getId());
            if (etiqueta.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            entrada.setEtiqueta(etiqueta.get());
        }
        Entrada actualizado = entradaRepository.save(entrada);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (!entradaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        entradaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/etiqueta/{etiquetaId}")
    public ResponseEntity<List<Entrada>> obtenerPorEtiqueta(@PathVariable Long etiquetaId) {
        if (!etiquetaRepository.existsById(etiquetaId)) {
            return ResponseEntity.notFound().build();
        }
        List<Entrada> entradas = entradaRepository.findByEtiquetaId(etiquetaId);
        return ResponseEntity.ok(entradas);
    }
}