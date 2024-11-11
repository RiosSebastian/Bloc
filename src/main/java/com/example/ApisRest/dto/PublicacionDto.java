package com.example.ApisRest.dto;

import com.example.ApisRest.entity.Comentarios;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicacionDto {
    private long id;
    private String titulo;
    private String descripcion;
    private String contenido;
    private Set<Comentarios> comentariosSet;
}
