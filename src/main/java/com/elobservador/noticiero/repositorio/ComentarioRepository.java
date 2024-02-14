package com.elobservador.noticiero.repositorio;

import com.elobservador.noticiero.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByNoticiaId(Long id);

}


