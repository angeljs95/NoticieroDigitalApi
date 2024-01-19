package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.entidades.NoticiaDto;
import com.elobservador.noticiero.entidades.Usuario;
import com.elobservador.noticiero.excepcions.MiExceptions;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public interface NoticiaServicee{


    Noticia createNoticia(Noticia noticia) throws  MiExceptions;

    Noticia getNoticia(long id);
    Noticia updateNoticia(Noticia noticia) throws MiExceptions;

    void deleteNoticia(long id ) throws MiExceptions;

    List<Noticia> listNoticias();



}
