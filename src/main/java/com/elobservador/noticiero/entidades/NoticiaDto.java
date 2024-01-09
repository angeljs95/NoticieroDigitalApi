package com.elobservador.noticiero.entidades;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NoticiaDto {

    private String titulo;
    private String copete;
    private String cuerpo;

    @ManyToOne
    private Periodista periodista;

    private Imagen imagen;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Imagen> albumImagenes;

    private boolean estadoNoticia;

    @Temporal(TemporalType.DATE)
    private Date alta;

    @OneToMany
    private List<Comentario> comentarios;

}
