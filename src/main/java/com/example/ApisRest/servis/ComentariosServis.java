package com.example.ApisRest.servis;

import com.example.ApisRest.dto.ComentariosDto;

public interface ComentariosServis {
    public ComentariosDto crearComentario(long publicacionid, ComentariosDto comentariosDto);
}
