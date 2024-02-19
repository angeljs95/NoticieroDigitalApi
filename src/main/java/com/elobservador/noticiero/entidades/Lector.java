package com.elobservador.noticiero.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name= "Lectores")
@PrimaryKeyJoinColumn(name = "id")
public class Lector extends Usuario{

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comentario> comentarios = new ArrayList<>();

    /*@OneToMany
    @JoinTable(
            name = "likes",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "noticia_id"))
    private List<> likesNotices = new ArrayList<>();*/

    @OneToMany(mappedBy = "lector", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "noticiasFavoritas",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "noticia_id"))
    private Set<Noticia> noticiasFavoritas = new HashSet<>();


    public Lector(){}


   /* public List<Noticia> getLikesNotices() {
        return likesNotices;
    }

    public void setLikesNotices(List<Noticia> likesNotices) {
        this.likesNotices = likesNotices;
    }*/

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lector lector = (Lector) o;
        return Objects.equals(comentarios, lector.comentarios) /*&& Objects.equals(likesNotices, lector.likesNotices)*/ && Objects.equals(noticiasFavoritas, lector.noticiasFavoritas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comentarios/*, likesNotices*/, noticiasFavoritas);
    }

    @Override
    public String toString() {
        return "Lector{" +
                "comentarios=" + comentarios +
                ", likes=" + likes +
                ", noticiasFavoritas=" + noticiasFavoritas +
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
