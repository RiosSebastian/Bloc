package com.example.ApisRest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Publicacion", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"})})
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name= "titulo", nullable = false)
    private String titulo;
    @Column(name= "descripcion", nullable = false)
    private String descripcion;
    @Column(name= "contenido", nullable = false)
    private String contenido;


}
