package com.example.ApisRest.servis;

import com.example.ApisRest.dto.PublicacionDto;
import com.example.ApisRest.entity.Publicacion;
import com.example.ApisRest.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServisImpl implements PublicacionServis{

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public PublicacionDto crearPublicacion(PublicacionDto publicacionDto) {
        //convertimos de dto a entidad
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(publicacionDto.getTitulo());
        publicacion.setDescripcion(publicacionDto.getDescripcion());
        publicacion.setContenido(publicacionDto.getContenido());

        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);

        //convertimos de entidad a dto
        PublicacionDto publicacionRespuesta = new PublicacionDto();
        publicacionRespuesta.setId(nuevaPublicacion.getId());
        publicacionRespuesta.setTitulo(nuevaPublicacion.getTitulo());
        publicacionRespuesta.setDescripcion(nuevaPublicacion.getDescripcion());
        publicacionRespuesta.setContenido(nuevaPublicacion.getContenido());

        return publicacionRespuesta;
    }
}
