package com.elobservador.noticiero.repositorio;

import com.elobservador.noticiero.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
