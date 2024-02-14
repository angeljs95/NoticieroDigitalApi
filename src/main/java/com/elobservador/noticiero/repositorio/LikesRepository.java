package com.elobservador.noticiero.repositorio;

import com.elobservador.noticiero.entidades.LikeId;
import com.elobservador.noticiero.entidades.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, LikeId> {

}
