package com.elobservador.noticiero.dtos;

import lombok.Data;

@Data
public class ComentarioDto {
    private Long idComentario;
    private String mensaje;
    private String lectorId;
    private Long noticiaId;
    private String periodistaId;


}
