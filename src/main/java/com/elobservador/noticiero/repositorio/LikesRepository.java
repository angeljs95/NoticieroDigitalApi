package com.elobservador.noticiero.repositorio;

import com.elobservador.noticiero.entidades.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    Likes findByNoticiaIdAndLectorId(Long noticiaId, String lectorId);

    List<Likes> findByLectorId(String lectorId);

    List<Likes> findByNoticiaId(Long id);

}
