package com.elobservador.noticiero.entidades;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Periodistas")
@PrimaryKeyJoinColumn(name = "id")
public class Periodista extends Usuario {


    private Integer matricula;

    @OneToMany(mappedBy = "periodista", fetch = FetchType.LAZY)
    private List<Imagen> imagenesPeriodista = new ArrayList<>();

    @OneToMany
    @Column(name="periodista_noticias")
    @JoinColumn(name = "noticia_id")
    private List<Noticia> noticias;

    //-------------------------Empiezan constructores, getters and setters ---------------------

    public Periodista(){}


    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public List<Imagen> getImagenesPeriodista() {
        return imagenesPeriodista;
    }

    public void setImagenesPeriodista(List<Imagen> imagenesPeriodista) {
        this.imagenesPeriodista = imagenesPeriodista;
    }

    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }
}
