package com.elobservador.noticiero.entidades;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    public String mensaje;

    @Temporal(TemporalType.DATE)
    public Date fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="lector_id")
    public Lector lector;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="periodista_id")
    public Periodista periodista;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "noticia_id") // Asegúrate de especificar el nombre de la columna en la tabla Comentario
    private Noticia noticia;


    //-------------------------Empiezan constructores, getters and setters ---------------------

    public Comentario(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return id == that.id && Objects.equals(mensaje, that.mensaje) && Objects.equals(fechaCreacion, that.fechaCreacion) && Objects.equals(lector, that.lector) && Objects.equals(periodista, that.periodista) && Objects.equals(noticia, that.noticia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mensaje, fechaCreacion, lector, periodista, noticia);
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                ", usuario=" + lector +
                ", periodista=" + periodista +
                '}';
    }
}
