package com.company.spring_boot_crud_app;

import org.springframework.data.repository.CrudRepository;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
        Usuario findByNombre(String nombre);
}
