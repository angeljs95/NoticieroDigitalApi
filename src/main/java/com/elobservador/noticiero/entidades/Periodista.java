package com.elobservador.noticiero.entidades;


import com.elobservador.noticiero.enumerations.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Periodistas")
@PrimaryKeyJoinColumn(name = "id")
public class Periodista extends Usuario {


    private Integer matricula;

    @OneToMany(fetch = FetchType.LAZY) //mappedBy = "periodista",
    private List<Imagen> imagenesPeriodista = new ArrayList<>();

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    private List<Noticia> noticias = new ArrayList<>();

    //-------------------------Empiezan constructores, getters and setters ---------------------

    public Periodista(){}

    public Periodista(String id, String name, Integer document, Integer age, String email, String password, String nickName, String address, Role role, boolean active, Imagen imagen, Integer matricula, List<Imagen> imagenesPeriodista, List<Comentario> comentarios, List<Noticia> noticias) {
        super(id, name, document, age, email, password, nickName, address, role, active, imagen);
        this.matricula = matricula;
        this.imagenesPeriodista = imagenesPeriodista;
        this.comentarios = comentarios;
        this.noticias = noticias;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
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


    public List<Noticia> getNoticias() {
        return noticias;
    }

    public void setNoticias(List<Noticia> noticias) {
        this.noticias = noticias;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodista that = (Periodista) o;
        return Objects.equals(matricula, that.matricula) && Objects.equals(imagenesPeriodista, that.imagenesPeriodista) && Objects.equals(comentarios, that.comentarios) && Objects.equals(noticias, that.noticias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula, imagenesPeriodista, comentarios, noticias);
    }

    @Override
    public String toString() {
        return "Periodista{" +
                "matricula=" + matricula +
                ", imagenesPeriodista=" + imagenesPeriodista +
                ", comentarios=" + comentarios +
                ", noticias=" + noticias +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", document=" + document +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", active=" + active +
                ", imagen=" + imagen +
                '}';
    }
}
