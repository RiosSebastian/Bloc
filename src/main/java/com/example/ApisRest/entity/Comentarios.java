package com.example.ApisRest.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.example.ApisRest.entity.Publicacion;

@Entity
@Table(name= "comentarios")
@Data
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    private String nombre;
    private String email;
    private String cuerpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;
}
