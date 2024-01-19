
package com.elobservador.noticiero.service;

import com.elobservador.noticiero.entidades.Noticia;
import com.elobservador.noticiero.excepcions.MiExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elobservador.noticiero.repositorio.NoticiaDaoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;


@Service
@Transactional
public class NoticiaService {
    
    @Autowired
    NoticiaDaoRepository noticiaDaoRepository;

    //---------------------Noticia Service sin usar Implements------------------

    public void crearNoticia(String titulo, String cuerpo, String copete ) throws MiExceptions {
         
        Noticia noticia= new Noticia();
        
        validar(titulo,cuerpo,copete);
        noticia.setCopete(copete);
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
      //  noticia.setVisualizar(true);
       
        noticiaDaoRepository.save(noticia);
    }
    
    public List<Noticia> listarNoticias(){
        
        List<Noticia> noticias= new ArrayList();
        
        noticias= noticiaDaoRepository.findAll();
        return noticias;
    }


    public void modificarNoticia(String titulo, String cuerpo, String copete, long id ) throws MiExceptions {
        validar( titulo,cuerpo, copete);
        Optional <Noticia> respuesta= noticiaDaoRepository.findById(id);
        
        if(respuesta.isPresent()){
            Noticia noticia = respuesta.get();
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            noticiaDaoRepository.save(noticia);
        }
    }
    
    public void darBaja(Long id){
        
        Optional <Noticia> respuesta= noticiaDaoRepository.findById(id);
        if(respuesta.isPresent()){
            Noticia noticia=respuesta.get();
           // noticia.setVisualizar(false);
            noticiaDaoRepository.save(noticia);
        }
        
    }
    
    public Noticia getOne(Long id){
       return noticiaDaoRepository.getOne(id);
        
    }
    
    public void validar(String titulo, String cuerpo, String copete) throws MiExceptions {
        

        
        if(titulo.isEmpty()|| titulo == null){
            throw new MiExceptions("El titulo no puede estar vacio o nulo");
            
            
        }
        if(cuerpo.isEmpty()|| cuerpo == null){
             throw new MiExceptions("El cuerpo tiene que tener algun contenido");
            
        }

        if(copete.isEmpty()|| cuerpo == null){
            throw new MiExceptions("El copete tiene que tener algun contenido");

        }
        
    }
    
}
