package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bodegas")
@Data
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column
    private String direccion;

    @Column
    private String telefono;

    @Column
    private Integer capacidad;

    @Column
    private Boolean activo = true;
}