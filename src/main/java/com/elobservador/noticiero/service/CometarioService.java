package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Comentario;
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
    private ComentarioRepository comentarioRepository;

    @Transactional
    public Comentario crearComentario (Comentario comentario){

        if (comentario!= null) {

            //Comentario comentario1= comentarioRepository.findById(comentario.getId());
            Comentario comentario1 = new Comentario();
            comentario1.setMensaje(comentario.getMensaje());
            comentario1.setNoticia(comentario.getNoticia());
            comentario1.setFechaCreacion(new Date());

            if(comentario.getPeriodista()!= null) {
                comentario1.setPeriodista(comentario.getPeriodista());
            } else {
                comentario1.setLector(comentario.getLector());
            }
           return comentarioRepository.save(comentario);
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

        public Comentario getOne (Long id){
        return comentarioRepository.getOne(id);

        }

        @Transactional(readOnly = true)
    public List<Comentario> getAll (){
            List<Comentario> comentarios= comentarioRepository.findAll();
        return  comentarios;

        }




    }


