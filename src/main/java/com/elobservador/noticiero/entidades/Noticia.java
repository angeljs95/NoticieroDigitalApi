
package com.elobservador.noticiero.entidades;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Noticia {
    
    @Id
    @GeneratedValue (strategy= GenerationType.SEQUENCE)
    private Long id;
    
    private String titulo;
    private String cuerpo;
    
    @Temporal(TemporalType.DATE)
    private Date alta;
    
    private boolean visualizar;
    
    public Noticia(){
        
    }

    public Noticia(Long id, String titulo, String cuerpo, Date alta, boolean visualizar) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.alta = alta;
        this.visualizar = visualizar;
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

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }
    
    
    
    
}
