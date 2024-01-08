package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Imagen;
import com.elobservador.noticiero.entidades.Periodista;

import java.util.List;

public interface ImagenService {

    Imagen createImagen(Imagen imagen);

    Imagen getImagen( String id);
    void updateImagen(Imagen imagen);

    void deleteImagen( String id);

    List<Imagen> listImagen();


}
