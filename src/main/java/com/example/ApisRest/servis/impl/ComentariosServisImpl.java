package com.example.ApisRest.servis.impl;

import com.example.ApisRest.dto.ComentariosDto;
import com.example.ApisRest.dto.PublicacionDto;
import com.example.ApisRest.entity.Comentarios;
import com.example.ApisRest.entity.Publicacion;
import com.example.ApisRest.excepciones.RecursoNotFoundException;
import com.example.ApisRest.repository.ComentariosRepository;
import com.example.ApisRest.repository.PublicacionRepository;
import com.example.ApisRest.servis.ComentariosServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentariosServisImpl implements ComentariosServis {

    @Autowired
    private ComentariosRepository comentariosRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public ComentariosDto crearComentario(long publicacionid, ComentariosDto comentariosDto) {
        Comentarios comentarios = mapearEntidad(comentariosDto);

        Publicacion publicacion = publicacionRepository.findById(publicacionid)
                .orElseThrow(()->new RecursoNotFoundException("Publicacion","id", publicacionid));

        comentarios.setPublicacion(publicacion);
        Comentarios nuevoComenterios = comentariosRepository.save(comentarios);
        return mapearDto(nuevoComenterios);
    }




    private ComentariosDto mapearDto(Comentarios comentarios){//convierte entidad a DTo
        ComentariosDto comentariosDto = new ComentariosDto();
        comentariosDto.setId(comentarios.getId());
        comentariosDto.setCuerpo(comentarios.getCuerpo());
        comentariosDto.setNombre(comentarios.getNombre());
        comentariosDto.setEmail(comentarios.getEmail());

        return  comentariosDto;
    }

    private Comentarios mapearEntidad(ComentariosDto comentariosDto){//convierte a DTo en entidad
        Comentarios comentarios = new Comentarios();

        comentarios.setId(comentariosDto.getId());
        comentarios.setCuerpo(comentariosDto.getCuerpo());
        comentarios.setNombre(comentariosDto.getNombre());
        comentarios.setEmail(comentariosDto.getEmail());

        return  comentarios;
    }
}
