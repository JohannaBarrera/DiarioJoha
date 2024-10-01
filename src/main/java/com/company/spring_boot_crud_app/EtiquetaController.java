package com.company.spring_boot_crud_app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {

    private final EtiquetaRepository etiquetaRepository;
    private final EntradaRepository productoRepository;

    public EtiquetaController(EtiquetaRepository etiquetaRepository, EntradaRepository productoRepository) {
        this.etiquetaRepository = etiquetaRepository;
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Etiqueta>> obtenerTodas() {
        List<Etiqueta> etiquetas = (List<Etiqueta>) etiquetaRepository.findAll();
        return ResponseEntity.ok(etiquetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etiqueta> obtenerPorId(@PathVariable Long id) {
        return etiquetaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Etiqueta> crearEtiqueta(@RequestBody Etiqueta etiqueta) {
        Etiqueta nuevaEtiqueta = etiquetaRepository.save(etiqueta);
        return new ResponseEntity<>(nuevaEtiqueta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etiqueta> actualizarEtiqueta(@PathVariable Long id, @RequestBody Etiqueta etiqueta) {
        if (!etiquetaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        etiqueta.setId(id);
        Etiqueta actualizada = etiquetaRepository.save(etiqueta);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEtiqueta(@PathVariable Long id) {
        if (!etiquetaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        // Verificar si hay productos asociados a esta categoría
        List<Entrada> productosAsociados = productoRepository.findByEtiquetaId(id);
        if (!productosAsociados.isEmpty()) {
            return ResponseEntity.badRequest().build(); // No se puede eliminar una categoría con productos asociados
        }
        etiquetaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<Entrada>> obtenerProductosPorEtiqueta(@PathVariable Long id) {
        if (!etiquetaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<Entrada> productos = productoRepository.findByEtiquetaId(id);
        return ResponseEntity.ok(productos);
    }
}