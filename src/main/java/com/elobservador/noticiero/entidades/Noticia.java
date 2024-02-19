
package com.elobservador.noticiero.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;


@Entity
@Table(name = "Noticias")
@PrimaryKeyJoinColumn(name = "id")
public class Noticia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String titulo;
    private String copete;
    private String cuerpo;

    @ManyToOne
    private Periodista periodista;
    @OneToOne
    private Imagen imagen;

    @OneToMany(fetch = FetchType.EAGER) //mappedBy = "noticia"
    private List<Imagen> albumImagenes = new ArrayList<>();

    private boolean estadoNoticia;

    @Temporal(TemporalType.DATE)
    private Date alta;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinTable(name = "Comentarios_Noticias",
            joinColumns = @JoinColumn(name = "noticia_id"),
            inverseJoinColumns = @JoinColumn(name = "comentario_id"))
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "noticia", cascade = CascadeType.ALL)
    private List<Likes> likes = new ArrayList<>();

    private  int cantidadDeMegusta = 0;

    @ManyToMany(mappedBy = "noticiasFavoritas")
    private Set<Lector> lectorsFavorit = new HashSet<>();



    //-------------------------Empiezan constructores, getters and setters ---------------------

    public Noticia(){

    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public int getCantidadDeMegusta() {
        return cantidadDeMegusta;
    }

    public void setCantidadDeMegusta(int cantidadDeMegusta) {
        this.cantidadDeMegusta = cantidadDeMegusta;
    }

    public void darLike ( ) {
        cantidadDeMegusta++ ;
    }

    public void sacarLike() {
        cantidadDeMegusta --;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCopete() {
        return copete;
    }

    public void setCopete(String copete) {
        this.copete = copete;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public boolean isEstadoNoticia() {
        return estadoNoticia;
    }

    public void setEstadoNoticia(boolean estadoNoticia) {
        this.estadoNoticia = estadoNoticia;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Imagen> getAlbumImagenes() {
        return albumImagenes;
    }

    public void setAlbumImagenes(List<Imagen> albumImagenes) {
        this.albumImagenes = albumImagenes;
    }

    public Set<Lector> getLectorsFavorit() {
        return lectorsFavorit;
    }

    public void setLectorsFavorit(Set<Lector> lectorsFavorit) {
        this.lectorsFavorit = lectorsFavorit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noticia noticia = (Noticia) o;
        return estadoNoticia == noticia.estadoNoticia && cantidadDeMegusta == noticia.cantidadDeMegusta && Objects.equals(id, noticia.id) && Objects.equals(titulo, noticia.titulo) && Objects.equals(copete, noticia.copete) && Objects.equals(cuerpo, noticia.cuerpo) && Objects.equals(periodista, noticia.periodista) && Objects.equals(imagen, noticia.imagen) && Objects.equals(albumImagenes, noticia.albumImagenes) && Objects.equals(alta, noticia.alta) && Objects.equals(comentarios, noticia.comentarios);// && Objects.equals(lectorLikes, noticia.lectorLikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, copete, cuerpo, periodista, imagen, albumImagenes, estadoNoticia, alta, comentarios, /*lectorLikes,*/ cantidadDeMegusta);
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", copete='" + copete + '\'' +
                ", cuerpo='" + cuerpo + '\'' +
                ", periodista=" + periodista +
                ", imagen=" + imagen +
                ", albumImagenes=" + albumImagenes +
                ", estadoNoticia=" + estadoNoticia +
                ", alta=" + alta +
                ", comentarios=" + comentarios +
                '}';
    }
}