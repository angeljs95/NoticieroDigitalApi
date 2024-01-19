package com.elobservador.noticiero.entidades;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Data
public class NoticiaDto {

    private String titulo;
    private String copete;
    private String cuerpo;

    @ManyToOne
    private Periodista periodista;

    private MultipartFile archivo;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Imagen> albumImagenes;

    private boolean estadoNoticia;

    @Temporal(TemporalType.DATE)
    private Date alta;

    @OneToMany
    private List<Comentario> comentarios;

}
