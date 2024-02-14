package com.elobservador.noticiero.entidades;

import javax.persistence.*;

@Entity
@IdClass(LikeId.class)
public class Likes {

    @Id
    @ManyToOne
    @JoinColumn(name = "lector_id")
    private Lector lector;

    @Id
    @ManyToOne
    @JoinColumn(name = "noticia_id")
    private Noticia noticia;

    public Likes(Lector lector, Noticia noticia) {
        this.lector = lector;
        this.noticia = noticia;
    }

    public Likes() {

    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }
}
