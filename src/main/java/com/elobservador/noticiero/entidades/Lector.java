package com.elobservador.noticiero.entidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name= "Lectores")
@PrimaryKeyJoinColumn(name = "id")
public class Lector extends Usuario{

    @OneToMany(mappedBy="lector", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentariosLector= new ArrayList<>();

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

    public List<Comentario> getComentariosLector() {
        return comentariosLector;
    }

    public void setComentariosLector(List<Comentario> comentariosLector) {
        this.comentariosLector = comentariosLector;
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
                "comentariosLector=" + comentariosLector +
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
