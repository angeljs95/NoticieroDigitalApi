package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Comentario;
import com.elobservador.noticiero.entidades.Periodista;

import java.util.List;

public interface CometarioService {


    Comentario createComentario(Comentario comentario);

    Comentario getComentario( String id);
    void updateComentario(Comentario comentario);

    void deleteComentario( String id);

    List<Comentario> listComentarios();


}
