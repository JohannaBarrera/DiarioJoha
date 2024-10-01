package com.company.spring_boot_crud_app;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "etiquetas")
@Data
public class Etiqueta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "etiqueta")
    private List<Entrada> entradas;

    @Override
    public String toString() {
        return "Etiqueta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}