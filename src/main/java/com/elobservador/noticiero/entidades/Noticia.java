
package com.elobservador.noticiero.entidades;

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

    @OneToMany(mappedBy = "noticia", fetch = FetchType.EAGER)
    private Set<Imagen> albumImagenes = new HashSet<>();

    private boolean estadoNoticia;

    @Temporal(TemporalType.DATE)
    private Date alta;

    @OneToMany(mappedBy = "noticia")
    private List<Comentario> comentarios = new ArrayList<>();


    //-------------------------Empiezan constructores, getters and setters ---------------------

    public Noticia(){

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

    public Set<Imagen> getAlbumImagenes() {
        return albumImagenes;
    }

    public void setAlbumImagenes(Set<Imagen> albumImagenes) {
        this.albumImagenes = albumImagenes;
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