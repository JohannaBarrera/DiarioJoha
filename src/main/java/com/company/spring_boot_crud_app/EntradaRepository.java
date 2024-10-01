package com.company.spring_boot_crud_app;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntradaRepository extends CrudRepository<Entrada, Long> {
    List<Entrada> findByNombre(String nombre);

    List<Entrada> findByEtiquetaId(Long etiquetaId);


}
