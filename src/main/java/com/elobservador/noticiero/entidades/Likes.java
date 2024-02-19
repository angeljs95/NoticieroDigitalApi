package com.elobservador.noticiero.entidades;

import javax.persistence.*;

@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "lector_id", unique = false)
    private Lector lector;

    @ManyToOne
    @JoinColumn(name = "noticia_id", unique = false)
    private Noticia noticia;

    public Likes(Lector lector, Noticia noticia) {
        this.lector = lector;
        this.noticia = noticia;
    }

    public Likes() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
