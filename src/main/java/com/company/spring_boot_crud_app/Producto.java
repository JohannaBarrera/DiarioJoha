// Esta línea define en qué "carpeta" (o paquete) está esta clase dentro del proyecto.
// Los paquetes ayudan a organizar el código.
package com.company.spring_boot_crud_app;

// A continuación, estamos "importando" o trayendo algunas herramientas que nos ayudarán
// a conectar esta clase con una base de datos. Es como traer ciertas funciones predefinidas 
// que necesitamos para que todo funcione sin tener que escribirlas desde cero.
import jakarta.persistence.*; // Nos trae todo lo necesario para trabajar con bases de datos.
import lombok.Data; // Nos trae una herramienta que nos permite reducir el código que tenemos que escribir.


@Entity // Indica que esta clase es una entidad que será gestionada por JPA (base de datos).
@Table(name = "productos") // Define que los datos de esta clase se almacenan en una tabla llamada "productos".
@Data // Lombok genera métodos como getters, setters, toString, equals y hashCode.
public class Producto {

    /**
     * El identificador único del producto. Se genera automáticamente por la base de datos.
     * 
     * <p>Este campo actúa como clave primaria en la tabla de la base de datos.</p>
     */
    @Id // Indica que este campo es la clave primaria de la entidad (ID único).
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La base de datos genera automáticamente el valor del ID.
    private Long id; // Identificador único del producto.

    /**
     * El nombre del producto. No puede ser nulo.
     * 
     * <p>Este campo almacena el nombre descriptivo del producto, por ejemplo, "Laptop", "Café", etc.</p>
     */
    @Column(nullable = false) // Este campo es obligatorio, no puede estar vacío.
    private String nombre; // Nombre del producto.

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;


}