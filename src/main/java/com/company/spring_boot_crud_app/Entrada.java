
package com.company.spring_boot_crud_app;

import java.text.DateFormat;
import java.util.Date;

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

    @Column(insertable = false)
    private Date fecha; 

    @ManyToOne
    @JoinColumn(name = "etiqueta_id", nullable = false)
    private Etiqueta etiqueta;
}