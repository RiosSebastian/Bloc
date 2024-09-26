package com.example.ApisRest.repository;

import com.example.ApisRest.entity.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long > {
}
