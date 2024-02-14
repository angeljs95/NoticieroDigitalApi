package com.elobservador.noticiero.entidades;

import java.io.Serializable;

public class LikeId implements Serializable {

    private Long noticia;
    private String lector;

    public LikeId() {

    }

    public LikeId(Long noticia, String lector) {
        this.noticia = noticia;
        this.lector = lector;
    }

    public Long getNoticia() {
        return noticia;
    }

    public void setNoticia(Long noticia) {
        this.noticia = noticia;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }
}
