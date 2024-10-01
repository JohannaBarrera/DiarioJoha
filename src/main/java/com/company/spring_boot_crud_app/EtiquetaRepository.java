package com.company.spring_boot_crud_app;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetaRepository extends CrudRepository<Etiqueta, Long> {
    List<Etiqueta> findByNombre(String nombre);
}