package com.example.ApisRest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicacionDto {
    private long id;
    private String titulo;
    private String descripcion;
    private String contenido;
}
