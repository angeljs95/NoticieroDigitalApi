package com.elobservador.noticiero.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "Lectores")
@PrimaryKeyJoinColumn(name = "id")
public class Lector extends Usuario{

    //@JsonBackReference
    @OneToMany(mappedBy = "lector", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "noticia_id"))
    private List<Noticia> likesNotices = new ArrayList<>();

    @OneToMany
    @Column(name="noticias_favoritas")
    @JoinColumn(name = "noticia_id")
   private Set<Noticia> noticiasFavoritas= new HashSet<>();

    public Lector(){}


    public List<Noticia> getLikesNotices() {
        return likesNotices;
    }

    public void setLikesNotices(List<Noticia> likesNotices) {
        this.likesNotices = likesNotices;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }


    public Set<Noticia> getNoticiasFavoritas() {
        return noticiasFavoritas;
    }

    public void setNoticiasFavoritas(Set<Noticia> noticiasFavoritas) {
        this.noticiasFavoritas = noticiasFavoritas;
    }


    @Override
    public String toString() {
        return "Lector{" +
                "comentarios=" + comentarios +
                ", like=" + likesNotices +
                ", noticiasFavoritas=" + noticiasFavoritas +
                ", name='" + name + '\'' +
                ", document=" + document +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", address='" + address + '\'' +
                ", role=" + role +
                ", imagen=" + imagen +
                '}';
    }
}
