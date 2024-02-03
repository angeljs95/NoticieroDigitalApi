package com.elobservador.noticiero.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToOne;

@Data
public class NoticiaDto {

    private Long id;
    private String titulo;
    private String copete;
    private String cuerpo;
    private String periodistaId;
    private MultipartFile archivo;



}
