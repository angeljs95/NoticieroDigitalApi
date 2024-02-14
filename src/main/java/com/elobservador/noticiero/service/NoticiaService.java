
package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.*;
import com.elobservador.noticiero.dtos.NoticiaDto;
import com.elobservador.noticiero.excepcions.MiExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import com.elobservador.noticiero.repositorio.NoticiaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class NoticiaService {
    
    @Autowired
    NoticiaRepository noticiaRepository;

    @Autowired
    PeriodistaService periodistaService;

    @Autowired
    ImagenService imagenService;
    @Autowired
    LectorService lectorService;

    public Noticia saveNoticia(NoticiaDto noticia) throws MiExceptions {

        validar(noticia);

        if(noticia.getPeriodistaId()== null) {
            return null;
        }
        Noticia noticiaNew= new Noticia();
        //atributos de entidad
        noticiaNew.setTitulo(noticia.getTitulo());
        noticiaNew.setCopete(noticia.getCopete());
        noticiaNew.setCuerpo(noticia.getCuerpo());
        noticiaNew.setEstadoNoticia(true);
        noticiaNew.setAlta(new Date());
        // atributos de relaciones
        Periodista periodista= periodistaService.getPeriodista(noticia.getPeriodistaId());
        Imagen imagen= imagenService.guardar(noticia.getArchivo());
        noticiaNew.setPeriodista(periodista);

        if (imagen != null) {
            noticiaNew.setImagen(imagen);
        }
       return noticiaRepository.save(noticiaNew);
    }
    
    public List<Noticia> getAllNews(){

        return noticiaRepository.findAll();
    }

    public Noticia updateNoticia(NoticiaDto noticia ) throws MiExceptions {
        validar(noticia);

        if(noticia.getId()==null){
           return null;
        }
        Optional <Noticia> respuesta= noticiaRepository.findById(noticia.getId());
        if(respuesta.isPresent()){
            Noticia noticiaActualizacion= respuesta.get();
            noticiaActualizacion.setTitulo(noticia.getTitulo());
            noticiaActualizacion.setCuerpo(noticia.getCuerpo());
            noticiaActualizacion.setCopete(noticia.getCopete());
            return noticiaRepository.save(noticiaActualizacion);
        }
        throw new MiExceptions("la noticia no pudo ser encontrada");
    }
    
    public void darBaja(Long idNoticia){
        
        Optional <Noticia> respuesta= noticiaRepository.findById(idNoticia);
        if(respuesta.isPresent()){
            Noticia noticia=respuesta.get();
          noticia.setEstadoNoticia(false);
            noticiaRepository.save(noticia);
        }
        
    }

    public void deleteNoticia(long id) throws MiExceptions{
        Optional<Noticia> respuesta= noticiaRepository.findById(id);
        if (respuesta.isPresent()){
            Noticia noticia= respuesta.get();
            noticiaRepository.delete(noticia);
        }else{
            throw new MiExceptions("La noticia no pudo encontrarse");
        }
    }
    
    public Noticia getNew(Long idNotice){

        return noticiaRepository.findById(idNotice).orElse(null);
    }

    public List<Noticia> newsByPeriodista( String idPeriodista){

        return noticiaRepository.findByPeriodistaId(idPeriodista);
    }

    public Noticia comentarNoticia(Comentario comentario) {
        if (comentario != null) {
            Noticia noticia = getNew(comentario.getNoticia().getId());
            noticia.getComentarios().add(comentario);
            return noticiaRepository.save(noticia);
        }
        return null;
    }


    private void validar(NoticiaDto noticia) throws MiExceptions{
        if (noticia.getTitulo()== null || noticia.getTitulo().isEmpty()){
            throw new MiExceptions("El titulo no puede estar en blanco o ser nulo ");
        }
        if (noticia.getCopete()== null || noticia.getCopete().isEmpty()){
            throw new MiExceptions("Debe agregar una reseña para la noticia");
        }
        if (noticia.getCuerpo()== null || noticia.getCuerpo().isEmpty()){
            throw new MiExceptions("El cuerpo de la noticia no puede estar en blanco o ser nulo ");
        }
    }

}


