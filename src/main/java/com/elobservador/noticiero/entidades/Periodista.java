package com.elobservador.noticiero.entidades;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Periodistas")
public class Periodista extends Usuario {

    private String nickName;

    private Integer matricula;

    @OneToMany
    private List<Imagen> imagenesPeriodista;

    @ManyToOne
    @JoinColumn(name = "noticia_id")
    private Noticia noticia;

    //-------------------------Empiezan constructores, getters and setters ---------------------

    public Periodista(){}

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }
}
