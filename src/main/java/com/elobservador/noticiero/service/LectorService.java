package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Comentario;
import com.elobservador.noticiero.entidades.Lector;
import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.excepcions.MiExceptions;
import com.elobservador.noticiero.repositorio.LectorRepository;
import com.elobservador.noticiero.repositorio.NoticiaDaoRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Optional;

public class LectorService {

     @Autowired
    private LectorRepository lectorRepository;

     @Autowired
     private NoticiaDaoRepository noticiaRepository;

     @Autowired
     private NoticiaServiceImp noticiaServiceImp;

     @Autowired
     private CometarioService cometarioService;

     @Transactional
    public void addComentario(Comentario comentario){

         if(comentario!= null) {

             Optional<Lector> respuesta = lectorRepository.findById(comentario.getLector().getId());
             if (respuesta.isPresent()) {
                 Lector lector = respuesta.get();
                 Comentario comentario1= cometarioService.crearComentario(comentario);
                 lector.getComentariosLector().add(comentario1);
                 lectorRepository.save(lector);
             }
         }
     }

    @Transactional
    public void RemoveComentario( Long id ) throws MiExceptions {
        try {
            Comentario comentario = cometarioService.getOne(id);
            if (comentario != null) {

                Optional<Lector> respuesta = lectorRepository.findById(comentario.getLector().getId());
                if (respuesta.isPresent()) {
                    Lector lector = respuesta.get();
                    cometarioService.deleteComentario(comentario.getId());
                    lector.getComentariosLector().remove(comentario);
                    lectorRepository.save(lector);
                }
            }
        } catch (MiExceptions ex) {
            ex.getMessage();
        }
    }
     public void darlike(Noticia noticia, String idLector ){

         Lector lector = lectorRepository.findById(idLector).orElseThrow(() -> new RuntimeException("Lector no encontrado"));
         Noticia noticia1 = noticiaRepository.findById(noticia.getId()).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

         lector.getLikesNotices().add(noticia);
         noticia1.getLectorLikes().add(lector);
         noticia1.darLike();
         lectorRepository.save(lector);
         noticiaRepository.save(noticia1);

      //   Optional<Noticia> noti= noticiaRepository.findById(noticia.getId());
//         if(noticia!= null){
//             noticia.darLike();
//         }
     }

     public void darUnlike(Noticia noticia, String idLector){
         Lector lector = lectorRepository.findById(idLector).orElseThrow(() -> new RuntimeException("Lector no encontrado"));
         Noticia noticia1 = noticiaRepository.findById(noticia.getId()).orElseThrow(() -> new RuntimeException("Noticia no encontrada"));

         lector.getLikesNotices().remove(noticia);
         noticia1.getLectorLikes().remove(lector);
         noticia1.sacarLike();
         lectorRepository.save(lector);
         noticiaRepository.save(noticia1);


     }





}
