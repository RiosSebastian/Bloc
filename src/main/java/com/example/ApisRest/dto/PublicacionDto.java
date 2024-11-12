package com.example.ApisRest.dto;

import com.example.ApisRest.entity.Comentarios;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty
    @Size(min=4 , message = "el titulo de la publicacion deberia tener al menos 4 caracteres ")
    private String titulo;

    @NotEmpty
    @Size(min=15 , message = "la descripcion de la publicacion deberia tener al menos 15 caracteres ")
    private String descripcion;

    @NotEmpty
    private String contenido;

    private Set<Comentarios> comentariosSet;
}
