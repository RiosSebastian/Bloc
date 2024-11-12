package com.example.ApisRest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ComentariosDto {

    private long id;
    @NotEmpty(message = "El nombre no debe ser nulo ")
    private String nombre;
    @NotEmpty(message = "El email no debe ser nulo ")
    @Email
    private String email;
    @NotEmpty
    @Size(min=10, message="El cuerpo  debe tener almenos 10 caracteres ")
    private String cuerpo;
}
