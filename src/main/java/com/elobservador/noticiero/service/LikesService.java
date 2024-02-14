package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Lector;
import com.elobservador.noticiero.entidades.LikeId;
import com.elobservador.noticiero.entidades.Likes;
import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.repositorio.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    @Autowired
    LikesRepository likesRepository;

    public Likes addLike(Likes like) {

        Likes newlike = new Likes();
        newlike.setNoticia(like.getNoticia());
        newlike.setLector(like.getLector());

        return likesRepository.save(newlike);

    }

    public void removeLike(Likes like) {
        if (like != null) {
            likesRepository.delete(like);
        }
    }

    public Likes getLike(LikeId id) {
        return likesRepository.getReferenceById(id);
    }


}
