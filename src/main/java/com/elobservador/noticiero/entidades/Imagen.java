package com.elobservador.noticiero.entidades;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
public class Imagen {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String mime;
    private String nombre;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    @Basic(fetch = FetchType.LAZY)
    private byte [] contenido;

    @ManyToOne
    @JoinColumn(name = "noticia_id")
    private Noticia noticia;

    @ManyToOne
    @JoinColumn(name = "periodista_id")
    private Periodista periodista;

    public Imagen(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
    }
}
