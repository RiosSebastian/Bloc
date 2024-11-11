package com.example.ApisRest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="Publicacion", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"})})
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    @Column(name= "titulo", nullable = false)
    private String titulo;
    @Column(name= "descripcion", nullable = false)
    private String descripcion;
    @Column(name= "contenido", nullable = false)
    private String contenido;
    @JsonBackReference
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comentarios> comentarios = new HashSet<>();


}
