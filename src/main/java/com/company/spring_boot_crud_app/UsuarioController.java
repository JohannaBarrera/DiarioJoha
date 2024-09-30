package com.company.spring_boot_crud_app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {



    private final UsuarioRepository usuarioRepository;


    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository =  usuarioRepository;

    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodas() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }




    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // @PostMapping
    // public ResponseEntity<Usuario> crearCategoria(@RequestBody Usuario usuario) {
    //     Categoria nuevaCategoria = usuarioRepository.save(usuario);
    //     return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
    //     if (!categoriaRepository.existsById(id)) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     categoria.setId(id);
    //     Categoria actualizada = categoriaRepository.save(categoria);
    //     return ResponseEntity.ok(actualizada);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
    //     if (!categoriaRepository.existsById(id)) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     // Verificar si hay productos asociados a esta categoría
    //     List<Producto> productosAsociados = productoRepository.findByCategoriaId(id);
    //     if (!productosAsociados.isEmpty()) {
    //         return ResponseEntity.badRequest().build(); // No se puede eliminar una categoría con productos asociados
    //     }
    //     categoriaRepository.deleteById(id);
    //     return ResponseEntity.noContent().build();
    // }

    // @GetMapping("/{id}/productos")
    // public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable Long id) {
    //     if (!categoriaRepository.existsById(id)) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     List<Producto> productos = productoRepository.findByCategoriaId(id);
    //     return ResponseEntity.ok(productos);
    // }
}