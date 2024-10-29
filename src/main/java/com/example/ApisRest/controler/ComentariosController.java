package com.example.ApisRest.controler;

import com.example.ApisRest.dto.ComentariosDto;
import com.example.ApisRest.servis.ComentariosServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ComentariosController {
    @Autowired
    private ComentariosServis comentariosServis;

    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentariosDto>guardarComentarios(@PathVariable (value = "publicacionId")long publicacionId, @RequestBody ComentariosDto comentariosDto){
        return new ResponseEntity<>(comentariosServis.crearComentario(publicacionId, comentariosDto), HttpStatus.CREATED);
    }
}
