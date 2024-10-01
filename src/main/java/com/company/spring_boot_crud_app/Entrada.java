
package com.company.spring_boot_crud_app;

import jakarta.persistence.*; 
import lombok.Data; 


@Entity
@Table(name = "entradas") 
@Data 
public class Entrada {


    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    @Column(nullable = false)
    private String nombre; 

    @ManyToOne
    @JoinColumn(name = "etiqueta_id", nullable = false)
    private Etiqueta etiqueta;
}