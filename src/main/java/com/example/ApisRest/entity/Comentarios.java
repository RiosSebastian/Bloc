package com.example.ApisRest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "name")
@Data
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String email;
    private String cuerpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id",nullable = false)
    private Publicacion publicacion;
}
