package com.elobservador.noticiero.service;

import com.elobservador.noticiero.dtos.ComentarioDto;
import com.elobservador.noticiero.entidades.Comentario;
import com.elobservador.noticiero.entidades.Lector;
import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.entidades.Periodista;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.repositorio.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CometarioService {

    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    NoticiaService noticiaService;
    @Autowired
    LectorService lectorService;

    @Transactional
    public Comentario crearComentario(ComentarioDto comentario) {

        if (comentario!= null) {

            Comentario newComentario = new Comentario();
            Noticia noticia = noticiaService.getNew(comentario.getNoticiaId());
            Lector lector = lectorService.getLector(comentario.getLectorId());
            Periodista periodista = noticia.getPeriodista();

            newComentario.setMensaje(comentario.getMensaje());
            newComentario.setFechaCreacion(new Date());
            newComentario.setNoticia(noticia);
            newComentario.setLector(lector);
            newComentario.setPeriodista(periodista);
            return comentarioRepository.save(newComentario);
        }
        return null;
        }

        @Transactional
        public void deleteComentario(long id) throws MiExceptions {

            Optional<Comentario> respuesta= comentarioRepository.findById(id);
            if (respuesta.isPresent()){
                comentarioRepository.deleteById(id);
            }
            else {
                throw new MiExceptions("No se ha podido encontrar el comentario");
            }
        }

    public Comentario getComentario(Long id) {
        return comentarioRepository.findById(id).orElse(null);

        }

    public List<Comentario> getCommentsByNotice(Long id) {

        return comentarioRepository.findByNoticiaId(id);


    }

    @Transactional(readOnly = true)
    public List<Comentario> getAll (){
            List<Comentario> comentarios= comentarioRepository.findAll();
        return  comentarios;

        }


}



