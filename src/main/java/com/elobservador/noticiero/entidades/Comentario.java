package com.elobservador.noticiero.entidades;

import jakarta.persistence.*;

@Entity
@Table(name="Comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    long id;

    public String mensaje;
    @ManyToOne
    public Usuario usuario;

    @ManyToOne
    public Periodista periodista;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", mensaje='" + mensaje + '\'' +
                ", usuario=" + usuario +
                ", periodista=" + periodista +
                '}';
    }
}
