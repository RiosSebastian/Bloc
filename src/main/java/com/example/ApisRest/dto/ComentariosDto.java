package com.example.ApisRest.dto;

import lombok.Data;

@Data
public class ComentariosDto {

    private long id;
    private String nombre;
    private String email;
    private String cuerpo;
}
