package com.example.ApisRest.repository;

import com.example.ApisRest.entity.Comentarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {

}
