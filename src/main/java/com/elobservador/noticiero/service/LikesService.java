package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Lector;
import com.elobservador.noticiero.entidades.Likes;
import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.repositorio.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    @Autowired
    LikesRepository likesRepository;
    @Autowired
    NoticiaService noticiaService;
    @Autowired
    LectorService lectorService;

    public Likes addLike(Lector lector, Noticia noticia) {

        Likes newlike = new Likes();
        newlike.setLector(lector);
        newlike.setNoticia(noticia);
        likesRepository.save(newlike);
        return newlike;

    }

    public String Likes(Lector lector, Noticia noticia) {
        Likes likes = likesRepository.findByNoticiaIdAndLectorId(noticia.getId(), lector.getId());
        if (likes != null) {
            removeLike(likes);
            noticiaService.unlike(likes);
            lectorService.unlike(likes);
            return "unlike " + noticia.getTitulo();
        }
        if (likes == null) {
            Likes newLike = addLike(lector, noticia);
            noticiaService.darlike(newLike);
            lectorService.addLike(newLike);
            return "like " + noticia.getTitulo();
        }
        return "null";
    }


    public void removeLike(Likes like) {
        if (like != null) {
            likesRepository.delete(like);
        }
    }

    public Likes getLike(Long id) {
        return likesRepository.findById(id).orElse(null);
    }

    public Likes findByLectorAndNoticia(Long idNoticia, String idLector) {

        return likesRepository.findByNoticiaIdAndLectorId(idNoticia, idLector);
    }


}

