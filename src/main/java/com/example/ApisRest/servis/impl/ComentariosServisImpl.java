package com.example.ApisRest.servis.impl;

import com.example.ApisRest.dto.ComentariosDto;
import com.example.ApisRest.dto.PublicacionDto;
import com.example.ApisRest.entity.Comentarios;
import com.example.ApisRest.entity.Publicacion;
import com.example.ApisRest.excepciones.BlocAppException;
import com.example.ApisRest.excepciones.RecursoNotFoundException;
import com.example.ApisRest.repository.ComentariosRepository;
import com.example.ApisRest.repository.PublicacionRepository;
import com.example.ApisRest.servis.ComentariosServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ComentariosDto> obtenerComentariosPorPublicacion(Long publicacionid) {
        List<Comentarios> comentariosPublicacion = comentariosRepository.findByPublicacionId(publicacionid);
        return comentariosPublicacion.stream().map(comentarios -> mapearDto(comentarios)).collect(Collectors.toList());
    }

    @Override
    public ComentariosDto obtenerComentariosPorId(Long publicacionid, Long comentariosId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionid)
                .orElseThrow(()->new RecursoNotFoundException("Publicacion","id", publicacionid));

        Comentarios comentarios = comentariosRepository.findById(comentariosId)
                .orElseThrow(()-> new RecursoNotFoundException("Comentarios","id", comentariosId));

        if(!comentarios.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlocAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }

        return mapearDto(comentarios);

    }

    @Override
    public ComentariosDto ActualizarComentarios(Long publicacionid, Long comentariosId, ComentariosDto solicitudDeComentario) {
        Publicacion publicacion = publicacionRepository.findById(publicacionid)
                .orElseThrow(()->new RecursoNotFoundException("Publicacion","id", publicacionid));

        Comentarios comentarios = comentariosRepository.findById(comentariosId)
                .orElseThrow(()-> new RecursoNotFoundException("Comentarios","id", comentariosId));

        if(!comentarios.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlocAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }
        comentarios.setNombre(solicitudDeComentario.getNombre());
        comentarios.setEmail(solicitudDeComentario.getEmail());
        comentarios.setCuerpo(solicitudDeComentario.getCuerpo());

        Comentarios comentarioActualizado = comentariosRepository.save(comentarios);
        return mapearDto(comentarioActualizado);
    }

    @Override
    public void EliminarComentario(Long publicacionid, Long comentariosId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionid)
                .orElseThrow(()->new RecursoNotFoundException("Publicacion","id", publicacionid));

        Comentarios comentarios = comentariosRepository.findById(comentariosId)
                .orElseThrow(()-> new RecursoNotFoundException("Comentarios","id", comentariosId));

        if(!comentarios.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlocAppException(HttpStatus.BAD_REQUEST, "El comentario no pertenece a la publicacion");
        }
        comentariosRepository.delete(comentarios);
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
