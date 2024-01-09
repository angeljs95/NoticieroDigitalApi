package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.entidades.NoticiaDto;
import com.elobservador.noticiero.entidades.Usuario;

import java.util.List;

public interface NoticiaServicee {

    NoticiaDto createNoticia(NoticiaDto noticiaDto);

    Noticia getNoticia( long id);
    void updateNoticia(Noticia noticia);

    void deleteNoticia(long id);

    List<Noticia> listNoticias();



}
